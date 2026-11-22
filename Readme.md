
# 🍃 LeafGuard AI
### AI-Based Plant Leaf Disease Detection Android Application

LeafGuard AI is an Android application that uses Artificial Intelligence and Machine Learning to identify plant leaf diseases from images. Users can select a picture of a plant leaf, and the trained AI model analyzes it to predict the disease class.

The project combines an AI image-classification model with an Android application to demonstrate how machine learning can be used in agriculture.

---

## 📌 Project Overview

Plant diseases can affect crop quality and agricultural productivity. Identifying diseases at an early stage can help users take appropriate action.

LeafGuard AI provides a simple way to analyze plant leaf images using a trained deep learning model integrated into an Android application.

## ✨ Main Features

- 📷 Select a plant leaf image from the gallery.
- 🤖 AI-based plant leaf disease classification.
- 🌿 Predicts one of the supported disease classes.
- 📱 Android application with a user-friendly interface.
- ⚡ On-device prediction using TensorFlow Lite.
- 📊 Displays the prediction result to the user.

## 🧠 AI and Machine Learning

The project uses a deep learning image-classification model.

**Model Information:**

| Component | Details |
|---|---|
| Model Type | Image Classification |
| Architecture | MobileNetV2 |
| Dataset | PlantVillage |
| Input Image Size | 224 × 224 |
| Input Shape | `[1, 224, 224, 3]` |
| Output Shape | `[1, 38]` |
| Model Format | TensorFlow Lite |
| Supported Classes | 38 |

The model was prepared and trained using Google Colab. After training, it was converted into TensorFlow Lite format so that it could be integrated into the Android application.

## 🛠️ Technologies Used

- **Java** – Android application development
- **Android Studio** – Development environment
- **XML** – User interface design
- **Python** – Machine learning model development
- **Google Colab** – Model training environment
- **TensorFlow / Keras** – Deep learning
- **TensorFlow Lite** – On-device model inference
- **PlantVillage Dataset** – Plant leaf image dataset

## ⚙️ How It Works

1. The user opens the LeafGuard AI application.
2. The user selects a plant leaf image from the gallery.
3. The selected image is prepared for model input.
4. The TensorFlow Lite model analyzes the image.
5. The model generates a prediction.
6. The application displays the predicted class.

## 📱 Application Screenshots

The following screenshots demonstrate the Android application's interface and functionality.

> Place your application screenshots in a folder named `screenshots` inside the repository.

| Application Screen | Preview |
|---|---|
| Screen 1 | ![Screen 1](screenshots/l1.png) |
| Screen 2 | ![Screen 2](screenshots/l2.png) |
| Screen 3 | ![Screen 3](screenshots/l3.png) |
| Screen 4 | ![Screen 4](screenshots/l4.png) |
| Screen 5 | ![Screen 5](screenshots/l5.png) |
| Screen 6 | ![Screen 6](screenshots/l6.png) |

## 🏗️ Development Journey

The project was developed step by step, from the initial Android Studio setup to AI model integration and application testing.

The development process included:

1. Creating the Android Studio project.
2. Designing the application interface.
3. Preparing the PlantVillage dataset.
4. Setting up Google Colab.
5. Preparing and processing image data.
6. Training the image-classification model.
7. Converting the trained model into TensorFlow Lite format.
8. Integrating the model into the Android application.
9. Implementing image selection and processing.
10. Connecting model predictions with the application interface.
11. Testing the application.

The development screenshots are provided in the `development` folder.

## 📂 Project Structure

```text
LeafGuard-AI/
│
├── README.md
│
├── app/
│   └── Android application source code
│
├── development/
│   ├── ll1.png
│   ├── ll2.png
│   ├── ll3.png
│   └── ... ll21.png
│
├── screenshots/
│   ├── l1.png
│   ├── l2.png
│   ├── l3.png
│   ├── l4.png
│   ├── l5.png
│   └── l6.png
│
└── model/
    ├── model.tflite
    └── labels.txt
```

*Note: This is a suggested repository structure. Adjust folder names to match your actual project.*

## 🚀 Installation and Setup

1. Download or clone this repository.
2. Open the project in Android Studio.
3. Allow Gradle to synchronize.
4. Connect an Android device or start an emulator.
5. Build and run the application.

**Note:** Ensure that the required TensorFlow Lite model and label files are included in the correct project locations.

## 🎯 Project Objectives

- Understand practical applications of Artificial Intelligence.
- Develop an image-classification model.
- Learn how to train a deep learning model.
- Convert a trained model into TensorFlow Lite format.
- Integrate AI functionality into an Android application.
- Understand the connection between machine learning and mobile app development.

## ⚠️ Limitations

- Prediction accuracy depends on image quality and model training.
- The model supports only its trained disease classes.
- Predictions should be treated as guidance, not as a professional agricultural diagnosis.
- Unrecognized or unclear images may produce unreliable results.

## 🔮 Future Improvements

- Add camera-based image capture.
- Improve model accuracy with additional training data.
- Display disease information and prevention guidance.
- Add prediction confidence information.
- Support additional plant species.

## 👩‍💻 Developer

**Maira Masood**  
BS Computer Science – 5th Semester  
KICSIT Kahuta Campus  
Course: Mobile Application Development

## 📚 Acknowledgements

- PlantVillage Dataset
- TensorFlow and Keras
- TensorFlow Lite
- Google Colab
- Android Developers Documentation

---

⭐ **LeafGuard AI – Bringing Artificial Intelligence into Plant Health Detection.**
