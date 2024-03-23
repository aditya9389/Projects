# chat_app.py

from flask import Flask, request, jsonify

app = Flask(__name__)

# In-memory storage for chat logs (you should use a database in production)
chat_logs = []

@app.route('/')
def index():
    return "Welcome to the Chat Application!"

@app.route('/chat', methods=['POST'])
def chat():
    try:
        user_message = request.json.get('message')
        if user_message:
            # Save user message to chat logs
            chat_logs.append(user_message)

            # Your logic to process the user message (e.g., call ChatGPT API)
            # For now, let's just echo the user's message
            response_message = f"You said: {user_message}"
            chat_logs.append(response_message)

            return jsonify({"response": response_message})
        else:
            return jsonify({"error": "Invalid input. Please provide a message."})
    except Exception as e:
        return jsonify({"error": str(e)})

if __name__ == '__main__':
    app.run(debug=True)
