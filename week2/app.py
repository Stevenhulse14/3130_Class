# Import necessary libraries
from sklearn.datasets import load_iris
from sklearn.model_selection import train_test_split
from sklearn.neighbors import KNeighborsClassifier
from sklearn.metrics import accuracy_score, classification_report, confusion_matrix

# Load the Iris dataset
iris = load_iris()
X = iris.data  # Features: sepal_length, sepal_width, petal_length, petal_width
y = iris.target  # Target: species (0=setosa, 1=versicolor, 2=virginica)

# Split data into training and testing sets (deterministic with random_state)
X_train, X_test, y_train, y_test = train_test_split(
    X, y, test_size=0.3, random_state=42
)

# Create k-NN classifier with k=3
knn = KNeighborsClassifier(n_neighbors=3)

# Train the model
knn.fit(X_train, y_train)

# Make predictions on test set
y_pred = knn.predict(X_test)

# Evaluate the model
accuracy = accuracy_score(y_test, y_pred)
print(f"Accuracy: {accuracy * 100:.2f}%")
print("\nClassification Report:")
print(classification_report(y_test, y_pred, target_names=iris.target_names))
print("\nConfusion Matrix:")
print(confusion_matrix(y_test, y_pred))

# Example: Predict species for a new flower
new_flower = [[5.1, 3.5, 1.4, 0.2]]  # sepal_length, sepal_width, petal_length, petal_width
prediction = knn.predict(new_flower)
predicted_species = iris.target_names[prediction[0]]
print(f"\nNew flower prediction: {predicted_species}")

## **Why This is Deterministic**

