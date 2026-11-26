# Bulk HR Email Automation System

A Java Spring Boot REST API application that automates job application emails to HR and recruitment professionals. Supports one-to-one email sending, bulk email campaigns, personalized templates, resume and cover letter attachments, email history, retry management, and Excel/CSV reporting.

## Project Overview

**Project Name:** Bulk HR Email Automation System  
**Backend:** Java 17 + Spring Boot 4.2.0  
**Database:** PostgreSQL 16  
**Build Tool:** Maven  
**Email Service:** Gmail SMTP  
**Application Type:** REST API (No Frontend)

## Key Features

### 1. HR Contact Management
- Add, update, delete and view HR contacts.
- Search by HR name, company, email, job role and location.
- Prevent duplicate email addresses.
- Store HR Name, Email ID, Company Name, Job Role and Location.

### 2. Excel Contact Import
- Upload HR contact lists through Excel.
- Support alternative Excel column names.
- Validate email addresses.
- Detect duplicate contacts.
- Import up to 500 contacts per upload.
- Report imported, skipped and failed records.

### 3. One-to-One Email Sending
- Send personalized emails to individual HR contacts.
- Dynamic subject and message.
- Attach resume PDF.
- Attach optional cover letter PDF.
- Store email sending history.

### 4. Bulk Email Automation
- Send personalized emails to up to 500 selected HR contacts per job.
- Background email processing.
- Configurable delay between emails.
- Track email progress.
- Pause and resume bulk jobs.
- Individual email status tracking.

### 5. Email Template Management
- Create and update email templates.
- Preview personalized messages.
- Dynamic placeholders:

| Placeholder | Description |
|---|---|
| `{{HR_NAME}}` | HR name |
| `{{EMAIL_ID}}` | HR email |
| `{{COMPANY_NAME}}` | Company name |
| `{{JOB_ROLE}}` | Job position |
| `{{LOCATION}}` | Job location |
| `{{YOUR_NAME}}` | Sender name |

### 6. Email Retry Management
- Retry individual failed emails.
- Retry all eligible failed emails in a bulk job.
- Maximum 3 retry attempts.
- Store retry count and error messages.
- Avoid retrying successful emails.

### 7. Email History and Reports
- Store email history in PostgreSQL.
- Track SENT, FAILED and PENDING statuses.
- Search and filter email history.
- View individual email details.
- Export reports to Excel (`.xlsx`).
- Export reports to CSV.

### 8. Production Setup (Planned)
- API authentication and authorization.
- Secure environment configuration.
- Swagger/OpenAPI documentation.
- Temporary attachment cleanup.
- Bulk job recovery after restart.
- Docker deployment.
- Database backup guidance.

## Technology Stack

| Technology | Purpose |
|---|---|
| Java 17 | Programming language |
| Spring Boot 4.2.0 | Backend framework |
| Spring Data JPA | Database operations |
| PostgreSQL 16 | Database |
| Spring Mail | Email sending |
| Gmail SMTP | Email server |
| Apache POI 5.4.1 | Excel processing |
| Apache Commons CSV | CSV export |
| Maven | Dependency management |
| Postman | API testing |

## Project Structure

```text
OneMail/
│
├── src/main/java/com/akshay/onemail/
│   │
│   ├── config/
│   │   └── AsyncConfig.java
│   │
│   ├── controller/
│   │   ├── HrContactController.java
│   │   ├── ExcelImportController.java
│   │   ├── EmailTemplateController.java
│   │   ├── EmailSendingController.java
│   │   ├── BulkJobController.java
│   │   ├── BulkRetryController.java
│   │   └── EmailHistoryController.java
│   │
│   ├── dto/
│   │
│   ├── entity/
│   │   ├── HrContact.java
│   │   ├── EmailTemplate.java
│   │   ├── EmailHistory.java
│   │   ├── BulkJob.java
│   │   ├── EmailStatus.java
│   │   ├── BulkJobStatus.java
│   │   └── EmailRetryStatus.java
│   │
│   ├── repository/
│   │   ├── HrContactRepository.java
│   │   ├── EmailTemplateRepository.java
│   │   ├── EmailHistoryRepository.java
│   │   └── BulkJobRepository.java
│   │
│   ├── service/
│   │   ├── HrContactService.java
│   │   ├── ExcelImportService.java
│   │   ├── EmailTemplateService.java
│   │   ├── EmailSendingService.java
│   │   ├── BulkJobService.java
│   │   ├── BulkEmailWorker.java
│   │   ├── BulkRetryService.java
│   │   ├── EmailHistoryService.java
│   │   └── EmailExportService.java
│   │
│   └── BulkEmailApplication.java
│
├── src/main/resources/
│   └── application.properties
│
├── pom.xml
└── README.md
```

## Installation and Setup

### 1. Prerequisites

Install:
- JDK 17
- Maven
- PostgreSQL 16
- Postman
- IntelliJ IDEA or Eclipse

### 2. Clone the repository

```bash
git clone YOUR_GITHUB_REPOSITORY_URL
cd OneMail
```

### 3. Create PostgreSQL database

```sql
CREATE DATABASE OneMail;
```

### 4. Configure application.properties

```properties
spring.application.name=OneMail

server.port=8080

spring.datasource.url=jdbc:postgresql://localhost:5432/bulk_hr_email
spring.datasource.username=${DB_USERNAME:postgres}
spring.datasource.password=${DB_PASSWORD}

spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=${MAIL_USERNAME}
spring.mail.password=${MAIL_PASSWORD}

spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
spring.mail.properties.mail.smtp.starttls.required=true

spring.servlet.multipart.max-file-size=5MB
spring.servlet.multipart.max-request-size=12MB
```

### 5. Configure Gmail SMTP

1. Enable 2-Step Verification on your Google account.
2. Generate a Google App Password if your account supports it.
3. Configure the App Password using the `MAIL_PASSWORD` environment variable.
4. Never commit your Gmail password or App Password to GitHub.

### 6. Set environment variables

PowerShell:

```powershell
$env:DB_USERNAME="postgres"
$env:DB_PASSWORD="your_database_password"
$env:MAIL_USERNAME="your_email@gmail.com"
$env:MAIL_PASSWORD="your_google_app_password"
```

### 7. Run the application

```bash
mvn clean install
mvn spring-boot:run
```

Application URL:

```text
http://localhost:8080
```

## REST API Endpoints

### HR Contacts

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/contacts` | Add contact |
| GET | `/api/contacts` | Get contacts |
| GET | `/api/contacts/{id}` | Get contact |
| PUT | `/api/contacts/{id}` | Update contact |
| DELETE | `/api/contacts/{id}` | Delete contact |
| GET | `/api/contacts/search` | Search contacts |

### Excel Import

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/excel/upload` | Upload Excel contacts |

### Email Templates

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/templates` | Create template |
| GET | `/api/templates/{id}` | Get template |
| PUT | `/api/templates/{id}` | Update template |
| GET | `/api/templates/{id}/preview` | Preview template |

### One-to-One Email

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/emails/send` | Send single email |

### Bulk Email

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/bulk/jobs` | Create bulk job |
| POST | `/api/bulk/jobs/{id}/start` | Start job |
| POST | `/api/bulk/jobs/{id}/pause` | Pause job |
| GET | `/api/bulk/jobs/{id}` | Check job status |

### Retry Management

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/bulk/jobs/{jobId}/retry-failed` | Retry failed emails |
| POST | `/api/bulk/jobs/{jobId}/retry/{historyId}` | Retry one email |

### Email History and Export

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/email-history` | View and search history |
| GET | `/api/email-history/{id}` | View email details |
| GET | `/api/email-history/export/excel` | Download Excel |
| GET | `/api/email-history/export/csv` | Download CSV |

## Bulk Email Request Example

Endpoint:

```http
POST /api/bulk/jobs
```

Postman → Body → form-data:

| Key | Type | Example |
|---|---|---|
| contactIds | Text | 1,2,3,4,5 |
| templateId | Text | 1 |
| resume | File | resume.pdf |
| coverLetter | File | cover-letter.pdf |
| delayMillis | Text | 2000 |

Maximum recipients per bulk job: **500**

## Email Status

| Status | Meaning |
|---|---|
| PENDING | Waiting to be sent |
| SENT | Accepted by SMTP server |
| FAILED | Sending attempt failed |

Note: SMTP acceptance does not guarantee inbox delivery.

## Development Roadmap

| Version | Module | Status |
|---|---|---|
| Version 1 | Project Setup | Provided |
| Version 2 | PostgreSQL Database | Provided |
| Version 3 | HR Contact APIs | Provided |
| Version 4 | Excel Upload | Provided |
| Version 5 | Email Templates | Provided |
| Version 6 | One-to-One Email | Provided |
| Version 7 | Bulk Email Sending | Provided |
| Version 8 | Retry Management | Provided |
| Version 9 | History and Export | Provided |
| Version 10 | Production Setup | Planned |

## Important Production Notes

- Test with a small number of recipients before sending a large batch.
- Respect Gmail sending limits and applicable anti-spam requirements.
- Use authorization and secure access controls before exposing the APIs.
- Use persistent attachment storage for production deployments.
- Add database locking and recovery mechanisms before running multiple application instances.
- Paginate large history searches and stream large exports.
- Store SMTP acceptance separately from actual delivery or bounce confirmation.

## Future Enhancements

- Multiple email provider support.
- Scheduled email campaigns.
- Email delivery and bounce webhooks.
- Advanced analytics dashboard.
- Contact groups and campaign management.
- Email open and click tracking, subject to recipient consent and applicable privacy rules.

## Author

**Akshay Prajapati**

Project: Bulk HR Email Automation System

Built with Java, Spring Boot, PostgreSQL and Gmail SMTP.

---

**License:** Add a license file before distributing or publishing the project.
