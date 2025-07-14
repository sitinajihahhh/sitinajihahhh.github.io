import tkinter as tk

def get_response(user_input):
    user_input = user_input.lower()
    
    if "hello" in user_input or "hi" in user_input:
            return "Hello! How can I help you today?"
        
    elif "how are you" in user_input:
        return "I'm just a bot, but I'm doing fine. Thanks for asking!"
    
    elif "bye" in user_input:
        return "Goodbye! Have a great day."
    
    elif "help" in user_input:
        return "I can help you with basic questions. Try saying hello!"
    
    else:
        return "I'm not sure how to respond to that yet."
    
def send_message():
    user_input = entry_box.get()
    chat_log.insert(tk.END, "You: " + user_input + "\n")

    response = get_response(user_input)
    chat_log.insert(tk.END, "Chatbot: " + response + "\n")

    entry_box.delete(0, tk.END)

window = tk.Tk()
window.title("Chatbot")
window.geometry("400x500")

chat_log = tk.Text(window, bg="white", fg="black", font=("Times New Roman", 12))
chat_log.pack(padx=10, pady=10, fill=tk.BOTH, expand=True)

entry_box = tk.Entry(window, font=("Times New Roman", 12))
entry_box.pack(padx=10, pady=5i want , fill=tk.X)

send_button = tk.Button(window, text= "Send", font=("Times New Roman", 12), command=send_message)
send_button.pack(padx=10, pady=10)

entry_box.bind("<Return>", lambda event: send_message())

window.mainloop()
  
