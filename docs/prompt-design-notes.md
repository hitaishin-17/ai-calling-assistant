# Prompt Design Notes – AI Calling Assistant

This document explains how prompts are structured for the AI Calling Assistant, how fallback logic is handled, and how prompt design impacts voice clarity and user experience.

---

## Prompt Design Goals

Prompts must be:

- ✅ **Contextual** — personalized with lead/company info
- ✅ **Concise** — optimized for voice (TTS) delivery
- ✅ **Conversational** — natural, human-sounding tone
- ✅ **Flexible** — adaptable for multiple call paths and objections
- ✅ **Safe** — avoids aggressive sales behavior or ambiguous claims

---

## Example Base Prompt

This is a sample prompt sent to Groq-hosted LLaMA before a call:
You are Ava, a friendly AI assistant calling on behalf of {{company_name}}.

You’re calling {{lead_name}}, the {{lead_role}} at {{lead_company}}, who showed interest in our product last week.

Begin with a short, respectful greeting and confirm if this is a good time.

If they say yes, ask one short question to understand their interest.

If they hesitate or object, calmly offer to reschedule or send more information.

Keep it natural, polite, and brief — and always confirm next steps clearly.

---

##  Sample Output (Voice Spoken by TTS)

> “Hi James, this is Ava from BrightPath. You downloaded our AI brochure last week — is this a good time to chat for a minute?”

---

## Fallback Prompts

The system includes **fallback scripts** triggered by:

- Silence or long pauses
- Common objections (e.g. "Not interested", "Busy", "Too expensive")

### Example Fallback Prompts:

- “I completely understand — would it help if I sent you some info and followed up later?”
- “That makes sense. Just so I don’t bother you again — are you the right person to speak to about automation?”
- “No problem at all. Is there a better time this week I can schedule a call for you?”

---

## Tone Strategy

| Intent              | Tone                  | Example                                       |
|---------------------|-----------------------|-----------------------------------------------|
| Greeting            | Friendly, light        | “Hi, this is Ava from Acme AI — how are you?” |
| Objection handling  | Calm, empathetic       | “Totally get it — timing is everything.”      |
| Closing             | Confident but polite   | “Let me send you a quick calendar invite.”    |

---

## Safety & Compliance

- Avoids medical/financial/guaranteed claims
- Does not store or reuse sensitive info without consent
- GDPR-aware: PII is anonymized in logs unless opt-in

---

## Why This Matters

Well-crafted prompts reduce:

- Misunderstood AI responses
- Robotic-sounding calls
- Drop-offs due to awkward phrasing

And improve:

- First-call resolution
- Lead engagement
- Call completion rate

---

## Next Iteration Ideas

- Dynamic tone shift based on sentiment
- A/B testing variants of opening lines
- Prompt chaining with voice memory
- Support for multiple languages and regional accents

---
