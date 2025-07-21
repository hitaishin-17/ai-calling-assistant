# AI Calling Assistant (Java)
> A fully automated AI-powered calling assistant that engages leads, responds like a real salesperson, and books meetings — with zero human intervention — unless absolutely necessary.

---

## Overview

AI Calling Assistant is a smart voice automation tool designed for outbound sales. It combines contextual lead intelligence, lifelike TTS voice, and AI-powered response logic to simulate real human phone calls — and drive actual outcomes like booked appointments.

---

## Product Thinking Behind This Project

As a Product Manager, I’ve seen the friction in scaling outbound sales:

- Scripts are rigid and break under real-world objections.
- SDRs lose hours on low-quality leads.
- AI tools often stop at lead scoring — not action.

This project solves that by using **voice + AI** to go beyond dashboards and actually **talk to leads**.

| Skill                        | Demonstrated Through                                           |
|-----------------------------|----------------------------------------------------------------|
| **Voice + AI Workflow Design** | Twilio TTS integrated with LLM-based dynamic response generation |
| **AI Product Fluency**          | Used Groq-hosted LLaMA models for real-time call logic         |
| **System Thinking**             | End-to-end flow: lead prep → call strategy → booking           |
| **Human-in-the-Loop Awareness** | Detects unclear cases and gracefully routes to human fallback  |
| **Compliance Thinking**         | Designed with GDPR-conscious architecture and data handling    |
| **Cross-Functional Execution**  | Built API, voice layer, AI prompt logic, and calendar sync     |

This project reflects how I take a **real user pain**, translate it into **product logic**, and build a working MVP with measurable value.

---

##  Features

- AI-generated call strategy (using Groq + LLaMA)
- Real-time calling via Twilio + TTS
- Human-like voice with dynamic prompts
- Objection handling and fallback support
- **Graceful fallback to human rep** when uncertain or escalated
- Automatic appointment booking + calendar invite
- Designed with **GDPR awareness** (no PII stored without consent)
- Modular, API-first backend built in Java
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

## Features

- AI-generated call strategy (using Groq + LLaMA)
- Real-time calling via Twilio + TTS
- Human-like voice with dynamic prompts
- Objection handling and fallback support
- Automatic appointment booking + calendar invite
- Modular, API-first backend built in Java

---

## Getting Started

```bash
# Clone the repository
git clone https://github.com/hitaishin-17/ai-calling-assistant.git
cd ai-calling-assistant-java/backend

# Open in IntelliJ or your favorite IDE
# Make sure to add your Twilio + Groq keys in application.properties or .env

TWILIO_ACCOUNT_SID=your_sid
TWILIO_AUTH_TOKEN=your_token
TWILIO_PHONE_NUMBER=your_twilio_number

GROQ_API_KEY=your_groq_key

```

## Sample Flow
	1.	Lead data is passed via API
	2.	AI generates a custom call script
	3.	Twilio places a real-time voice call
	4.	LLM responds dynamically based on user replies
	5.	Appointment is booked and invite is sent
	6.	If AI is unsure or detects complexity → routes to a human rep

## Future Enhancements
	1. 	Streaming voice recognition (STT → AI → TTS loop)
 	2. 	Lead enrichment from CRM before calling
  	3. 	Multi-language support
   	4.	React dashboard for live call tracking
    	5. 	Zapier / Slack integration
     	6. 	GDPR-compliant logging & audit trail

## 📩 About the Creator

Hi, I’m Hitaishi N — a Product Manager passionate about intelligent systems and voice-first automation.

I built this to showcase:
	•	AI-powered execution, not just scoring
	•	Real-world friction turned into structured product
	•	Full-stack thinking — from prompt to phone to calendar

Let’s connect on [LinkedIn](https://www.linkedin.com/in/hitaishi-n-grovista)!
