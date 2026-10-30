package com.example.leafguardai;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import org.tensorflow.lite.Interpreter;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    ImageView leafImage;
    Button selectButton, analyzeButton;
    TextView resultText, confidenceText;

    Uri selectedImageUri;
    Interpreter tflite;
    List<String> labels = new ArrayList<>();

    private static final int IMAGE_SIZE = 224;
    private static final int NUM_CLASSES = 38;

    ActivityResultLauncher<String> imagePicker =
            registerForActivityResult(
                    new ActivityResultContracts.GetContent(),
                    uri -> {
                        if (uri != null) {
                            selectedImageUri = uri;
                            leafImage.setImageURI(uri);

                            resultText.setText("Image selected!");
                            confidenceText.setText(
                                    "Ready for AI analysis"
                            );
                        }
                    }
            );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        leafImage = findViewById(R.id.leafImage);
        selectButton = findViewById(R.id.selectButton);
        analyzeButton = findViewById(R.id.analyzeButton);
        resultText = findViewById(R.id.resultText);
        confidenceText = findViewById(R.id.confidenceText);

        try {
            loadModel();
            loadLabels();

            resultText.setText("AI model loaded successfully!");
            confidenceText.setText("Select a leaf image to begin.");

        } catch (Exception e) {
            resultText.setText("Model loading failed.");
            confidenceText.setText(e.getMessage());
        }

        selectButton.setOnClickListener(view ->
                imagePicker.launch("image/*")
        );

        analyzeButton.setOnClickListener(view -> {

            if (selectedImageUri == null) {
                Toast.makeText(
                        this,
                        "Please select a leaf image first",
                        Toast.LENGTH_SHORT
                ).show();
                return;
            }

            if (tflite == null || labels.size() != NUM_CLASSES) {
                Toast.makeText(
                        this,
                        "Model or labels are not ready",
                        Toast.LENGTH_LONG
                ).show();
                return;
            }

            predictDisease();
        });
    }

    private void loadModel() throws Exception {

        InputStream input = getAssets().open("leaf_model.tflite");
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        byte[] data = new byte[4096];
        int count;

        while ((count = input.read(data)) != -1) {
            output.write(data, 0, count);
        }

        input.close();

        byte[] modelBytes = output.toByteArray();

        ByteBuffer modelBuffer =
                ByteBuffer.allocateDirect(modelBytes.length);

        modelBuffer.order(ByteOrder.nativeOrder());
        modelBuffer.put(modelBytes);
        modelBuffer.rewind();

        tflite = new Interpreter(modelBuffer);
    }

    private void loadLabels() throws Exception {

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        getAssets().open("labels.txt")
                )
        );

        String line;

        while ((line = reader.readLine()) != null) {
            if (!line.trim().isEmpty()) {
                labels.add(line.trim());
            }
        }

        reader.close();

        if (labels.size() != NUM_CLASSES) {
            throw new Exception(
                    "Expected 38 labels, found " + labels.size()
            );
        }
    }

    private void predictDisease() {

        try {

            InputStream stream =
                    getContentResolver().openInputStream(selectedImageUri);

            if (stream == null) {
                throw new Exception("Cannot open selected image");
            }

            Bitmap originalBitmap = BitmapFactory.decodeStream(stream);
            stream.close();

            if (originalBitmap == null) {
                throw new Exception("Invalid image");
            }

            Bitmap resizedBitmap = Bitmap.createScaledBitmap(
                    originalBitmap,
                    IMAGE_SIZE,
                    IMAGE_SIZE,
                    true
            );

            // Prepare RGB float32 input
            ByteBuffer inputBuffer = ByteBuffer.allocateDirect(
                    1 * IMAGE_SIZE * IMAGE_SIZE * 3 * 4
            );

            inputBuffer.order(ByteOrder.nativeOrder());

            for (int y = 0; y < IMAGE_SIZE; y++) {
                for (int x = 0; x < IMAGE_SIZE; x++) {

                    int pixel = resizedBitmap.getPixel(x, y);

                    float red =
                            ((pixel >> 16) & 0xFF) / 127.5f - 1.0f;

                    float green =
                            ((pixel >> 8) & 0xFF) / 127.5f - 1.0f;

                    float blue =
                            (pixel & 0xFF) / 127.5f - 1.0f;

                    inputBuffer.putFloat(red);
                    inputBuffer.putFloat(green);
                    inputBuffer.putFloat(blue);
                }
            }

            inputBuffer.rewind();

            // Model prediction: 38 classes
            float[][] predictions = new float[1][NUM_CLASSES];

            tflite.run(inputBuffer, predictions);

            // Find highest prediction
            int bestIndex = 0;
            float bestScore = predictions[0][0];

            for (int i = 1; i < NUM_CLASSES; i++) {

                if (predictions[0][i] > bestScore) {
                    bestScore = predictions[0][i];
                    bestIndex = i;
                }
            }

            String diseaseName = labels.get(bestIndex);
            float confidence = bestScore * 100f;

            resultText.setText(
                    "Predicted Disease:\n" + diseaseName
            );

            confidenceText.setText(
                    String.format(
                            java.util.Locale.US,
                            "Model confidence: %.2f%%",
                            confidence
                    )
            );

            Toast.makeText(
                    this,
                    "Prediction completed!",
                    Toast.LENGTH_SHORT
            ).show();

            resizedBitmap.recycle();
            originalBitmap.recycle();

        } catch (Exception e) {

            resultText.setText("Prediction failed");

            confidenceText.setText(e.getMessage());

            Toast.makeText(
                    this,
                    "Error: " + e.getMessage(),
                    Toast.LENGTH_LONG
            ).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (tflite != null) {
            tflite.close();
            tflite = null;
        }
    }
}