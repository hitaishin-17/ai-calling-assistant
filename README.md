# AI Calling Assistant (Java)

> A fully automated AI sales caller that mimics a real human — it understands lead context, speaks naturally, handles objections, and even books meetings.

---

## Overview

This project brings together conversational AI, lifelike voice synthesis, and intelligent call flow logic to create a virtual sales rep. With zero human intervention, it:

- Prepares context from lead + company info
- Generates a dynamic strategy using AI
- Makes real-time calls using Twilio
- Handles objections and fallback scenarios
- Books calendar meetings automatically

---

## Tech Stack

| Layer         | Technology                   |
|---------------|------------------------------|
| Backend       | Java + Spring Boot           |
| Voice Calling | Twilio (TwiML, TTS)          |
| AI Engine     | Groq API (easily swappable)  |
| Prompt Design | Custom prompt scripting      |
| Frontend (Optional) | React (for dashboard)  |

---

---

## ✨ Features

- AI-generated, personalized call scripts
- Natural voice via Twilio TTS (Text-to-Speech)
- Handles objections and fallbacks
- Auto-schedules meetings post-call
- Modular architecture for easy extensibility

---

## Getting Started

```bash
# Clone the repository
git clone https://github.com/hitaishin-17/ai-calling-assistant.git
cd ai-calling-assistant-java/backend

# Open in IntelliJ or your favorite IDE
# Make sure to add your Twilio + Groq keys in application.properties or .env
```

## Sample Flow
	1.	Call is triggered via API or CLI
	2.	AI generates a call script based on prompt + lead data
	3.	Twilio places the call and speaks using TTS
	4.	AI responds to live inputs (or fallback scripts)
	5.	Appointment is booked + calendar invite sent
