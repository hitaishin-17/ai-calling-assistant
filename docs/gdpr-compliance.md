# Data Privacy & Compliance – AI Calling Assistant

This project is designed with strong privacy principles, complying with:

- 🇪🇺 **EU GDPR (General Data Protection Regulation)**
- 🇬🇧 **UK GDPR**
- 🇦🇺 **Australian Privacy Act 1988** and the **Australian Privacy Principles (APPs)**

Whether leads are based in the **EU, UK, or Australia**, this system respects data rights and minimizes exposure through careful design.

---

## ✅ Core Privacy Principles Followed

| Principle                  | Implementation in AI Calling Assistant                                                  |
|---------------------------|------------------------------------------------------------------------------------------|
| **Lawful Basis for Contact** | Calls are made only to leads with prior opt-in, legitimate interest, or business contact context. |
| **Transparency**          | Each call opens with clear identification as an AI assistant, including the purpose of the call. |
| **Data Minimization**     | Only minimal data is used: First name, Company, Role, and Business Email (no sensitive or health data). |
| **Consent & Opt-Out**     | Leads can opt out during the call or from calendar invites; consent is respected across future interactions. |
| **Purpose Limitation**    | Data is used solely for lead engagement, demo booking, or outbound qualification.         |
| **Storage Limitation**    | No personal data is stored long-term unless explicit consent is given. Logs are anonymized where possible. |
| **Security & Access**     | API calls are encrypted. API keys and tokens are stored securely and not exposed in code. |
| **Right to Access / Erasure** | We support user rights to request data deletion, correction, or access when identifiable information is retained. |

---

## Data Processed Per Call

| Field        | Usage                      | Retained?         |
|--------------|----------------------------|-------------------|
| First Name   | Personalization            | No (runtime only) |
| Company Name | Context for AI responses   | No (runtime only) |
| Role/Title   | Call relevance             | No (runtime only) |
| Email        | For calendar invites       | Yes (if invite sent) |
| Call Outcome | Status logging (anonymized) | Yes (no PII)      |
| Voice Data   | Not recorded or stored     | ❌ Disabled        |

---

## Human Intervention Triggers

In cases where the AI is uncertain or if the user requests escalation, the system may:

- Collect additional contact details with consent
- Transfer context to a human rep
- Log the issue without storing identifiable details unless necessary for follow-up

---

## Privacy by Design

- No use of tracking cookies or profiling
- No background audio recording or biometric use
- Environment variables (e.g., Twilio keys, Groq API keys) are never hardcoded
- `.env.example` provided to keep sensitive config isolated
- Optional integrations (e.g., CRM or email logging) must implement their own privacy checks

---

## Regional Notes

| Region      | Notes                                                                 |
|-------------|-----------------------------------------------------------------------|
| **EU/UK**   | GDPR compliant structure: no profiling, full consent, opt-out ready   |
| **Australia** | Aligned with APPs: transparency, access rights, storage limitation   |

---

## ❗ Developer Reminder

If you fork, deploy, or customize this project:

- Review your local data protection regulations
- Avoid logging or storing personal information unless necessary
- Add visible opt-out paths if you're sending follow-up communication

---

## Sample Disclosure (for script)

> "Hi, this is Ava — an AI assistant calling on behalf of Acme Analytics. This call is being placed for informational and scheduling purposes only. No data is stored unless you proceed to book a meeting or request follow-up. You can opt out any time."

---

## Questions?

This repo does not process live user data. For actual deployments, ensure your infrastructure includes:

- Consent tracking
- Data subject request handling (DSAR)
- Clear privacy policy for end users
