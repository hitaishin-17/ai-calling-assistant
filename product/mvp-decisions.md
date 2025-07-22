# MVP Decisions – AI Calling Assistant (PM Case Study)

This document highlights product thinking, MVP trade-offs, and strategy decisions behind the **AI Calling Assistant** — a voice automation MVP simulating natural conversations to qualify leads and trigger bookings.

---

## Product Vision

Build an AI-powered outbound voice agent that engages prospects via natural conversations, handles objections, and books appointments — **reducing human bandwidth at the top of the sales funnel**.

---

## MVP Goals

- **Business Objective**: Automate outbound qualification for sales teams (EdTech, B2B) while maintaining human-like interaction.
- **Target Users**: Prospective learners and cold leads across India (local demo) with roadmap to scale globally.
- **Success Criteria**:
  - Context-aware AI replies via LLM
  - Appointment booking interest detected
  - “Cut the call” intent handled gracefully
  - Voice UX > press-based IVRs

---

## What We Shipped (MVP Scope)

| Feature                              | Shipped | Why It Mattered                        |
|--------------------------------------|---------|----------------------------------------|
| Voice input via Twilio + TTS         | ✅      | Enables lifelike real-time interaction |
| LLM-powered replies via Groq         | ✅      | Replaces rigid IVRs with smart convo   |
| Booking prompt & time capture        | ✅      | Moves user to business outcome         |
| “Cut the call” trigger support       | ✅      | Mimics human call exits                |
| Hinglish prompt understanding        | ✅      | Showcased regional demo fluency        |

---

## What We Deferred (For Iteration)

| Feature                             | Reason Deferred                   | Next Steps      |
|-------------------------------------|-----------------------------------|-----------------|
| Long-term memory / user context     | MVP constraint                    | Planned upgrade |
| CRM or Calendly sync                | Local testing focus               | 🔜 Integration  |
| Custom voice cloning (TTS)          | Cost vs. demo ROI                 | Medium priority |
| Admin UI                            | Logs enough for early testing     | UI backlog    |

---

## Product Delivery Process

We followed **Agile delivery with a Kanban flow** inside JIRA for:

- Prioritizing user stories around reply intelligence
- Iterating prompt tuning + fallback handling
- Logging learning loops weekly
- Ensuring lean scope for voice-first UX

📎 Screenshot: ![Kanban Board](kanban-board.png)

---

## EPIC: Improve AI Response Quality

**User Story:**  
_As a user, I want the assistant to understand and reply contextually, not with generic scripts._

**Decisions:**
- Crafted persona-based system prompts (EdTech focus)
- Implemented fallback replies for vague or noisy inputs
- Captured user phrases like “cut the call” → trigger polite exit

---

## Compliance Thinking

Even as a demo, we factored in:

- **GDPR Awareness**: No PII stored, opt-in calls only
- **Modular Build**: Easy to containerize per region
- **Scalability**: API-ready for global sales workflows

---

## What This Shows

✅ Product prioritization under time/budget  
✅ MVP framing based on user journey  
✅ Systems thinking (voice, AI, UX, compliance)  
✅ Execution from prompt to outcome — not just ideas

---

## Demo Available

→ [Watch the call in action](https://drive.google.com/file/d/1t2GFwdMOtbZxoA1IWTVuvOu9nz4eYJW0/view)

---
