# Java Developer Upskill Roadmap

A practical, scenario-first roadmap for becoming a production-ready Java developer. Learn the language deeply, build reliable services, operate them safely, and then add distributed systems and AI capabilities.

## What Is Trending

- **Java 21 and Java 25 LTS:** records, sealed classes, pattern matching, switch expressions, virtual threads, and modern garbage collectors.
- **Spring Boot 3.x:** Spring Framework 6, Jakarta namespaces, configuration properties, Actuator, and production observability.
- **Cloud-native Java:** containers, Kubernetes, REST and gRPC, resilient clients, event-driven architecture, and service-to-service security.
- **Operational excellence:** OpenTelemetry, metrics, logs, traces, SLOs, profiling, graceful shutdown, and capacity planning.
- **Data engineering:** PostgreSQL, Redis, Kafka, schema migrations, CDC, idempotency, and polyglot persistence.
- **Security by design:** OAuth2/OIDC, JWT validation, mTLS, secrets management, OWASP API risks, and dependency scanning.
- **Delivery automation:** CI/CD, infrastructure as code, feature flags, progressive delivery, and automated rollback.
- **AI-enabled applications:** embeddings, retrieval-augmented generation, tool calling, evaluation, cost controls, and prompt-injection defense.

## Recommended Learning Path

### 1. Java Foundations

Master classes and interfaces, immutability, generics, collections, `equals`/`hashCode`, exceptions, I/O, lambdas, streams, and the Java Memory Model.

Build: a command-line expense tracker with validation, persistence, tests, and CSV import/export.

### 2. Modern Java and Concurrency

Use records for data carriers, sealed types for controlled models, pattern matching for readable branching, `CompletableFuture` for asynchronous composition, and virtual threads for blocking I/O workloads. Understand when locks, atomics, queues, and bounded executors are appropriate.

Build: a concurrent price aggregator with timeouts, cancellation, retries, and a bounded result cache.

### 3. Spring Boot APIs

Learn dependency injection, configuration, validation, exception handling, pagination, API versioning, OpenAPI, Spring Security, database transactions, and test slices.

Build: a versioned order API with PostgreSQL, Flyway migrations, role-based access, idempotent order creation, and contract tests.

### 4. Persistence and Messaging

Learn SQL before relying on an ORM. Understand indexes, query plans, isolation levels, locking, connection pools, N+1 queries, Kafka partitions, consumer groups, ordering, retries, dead-letter topics, and the outbox pattern.

Build: an order workflow that publishes reliable events and can replay them without creating duplicate payments.

### 5. Distributed Systems

Study timeouts, retries with jitter, circuit breakers, bulkheads, rate limits, eventual consistency, sagas, cache invalidation, backpressure, and graceful degradation. Make every remote call observable.

Build: a resilient checkout system with inventory, payment, and notification boundaries.

### 6. Cloud and Delivery

Containerize services with small, non-root images. Learn Kubernetes Deployments, Services, probes, resource requests and limits, Secrets, autoscaling, rolling releases, canaries, and rollback.

Build: a deployable service with health probes, automated security checks, canary release, and rollback documentation.

### 7. Observability and Performance

Instrument HTTP, database, and messaging paths with OpenTelemetry. Correlate logs with trace IDs, define useful metrics, and profile before optimizing. Measure p50, p95, and p99 latency rather than averages alone.

Build: an observable service with an SLO, load test, dashboard, and incident runbook.

### 8. Security

Threat-model the API, validate input, enforce authorization at the resource boundary, rotate secrets, protect sensitive logs, use secure headers, and keep dependencies current.

Build: a multi-tenant API with OIDC login, scoped access tokens, audit events, and security regression tests.

### 9. AI Application Engineering

Learn model APIs, streaming responses, embeddings, vector search, RAG, tool calling, structured output, token budgets, retries, fallbacks, and evaluation datasets. Treat retrieved text and model output as untrusted input; never let a model decide authorization.

Build: a documentation assistant that cites source passages, filters by tenant, records evaluation metrics, and has human approval for actions.

## Scenario-Based Interview and Design Questions

Use this answer format: **clarify requirements -> state assumptions -> propose design -> discuss failure modes -> explain trade-offs -> define tests and metrics**.

### Java Language and Collections

1. A `HashMap` lookup fails after a key object is mutated. What happened, and how do you fix the model?
2. A stream pipeline works in tests but produces incorrect totals in production. Which side effects or parallelism assumptions do you inspect?
3. A record contains a mutable list and callers can change it. How do you preserve immutability?
4. A method accepts `List<Object>` but callers have `List<String>`. How would you redesign the generic boundary?
5. A service catches `Exception` and continues after database failures. What information and failure behavior are lost?
6. A sealed hierarchy gains a subtype and compilation breaks in several switches. Why can that be useful?
7. A large object graph causes high allocation rates. How do you measure and reduce it without guessing?
8. Which cases are clearer or faster with ordinary loops than streams?

### Concurrency and Virtual Threads

1. Replacing a fixed pool with virtual threads increases database failures. What resource was bounded before, and how do you restore backpressure?
2. A counter is occasionally wrong under load. How do you choose between synchronization, locks, atomics, and a queue?
3. Two requests update the same account and one update disappears. Which consistency strategy would you use?
4. A `CompletableFuture` chain hangs intermittently. How do you find blocked executors, missing timeouts, and unobserved exceptions?
5. A task is cancelled, but the remote call continues. How should cancellation propagate across boundaries?
6. A virtual-thread application pins carrier threads. Which synchronized or native blocking sections do you inspect?
7. A producer is faster than a consumer. How do you implement bounded queues and a useful overload response?

### Spring Boot and REST APIs

1. A client retries after a timeout and creates two orders. How do you make the operation idempotent?
2. Each endpoint returns a different error shape. Where would you centralize error handling and validation?
3. A production configuration value is ignored. Which property sources, profiles, binding rules, and startup diagnostics do you check?
4. A `@Transactional` annotation has no effect. What proxy, self-invocation, visibility, or transaction-manager issue could explain it?
5. An API needs a breaking response change. How do you version it and deprecate the old contract?
6. A list endpoint becomes slow as data grows. How do you design pagination, indexes, limits, and response fields?
7. A downstream service is unavailable. What should the API return, and which fallback is safe?
8. A singleton bean contains request-specific state. What race or data leak can result?

### SQL, JPA, and Transactions

1. A page loads 101 SQL statements instead of one. How do you identify and fix an N+1 query?
2. Two users edit the same record. Compare optimistic and pessimistic locking for this workflow.
3. A transaction succeeds but a message is not published. How does the outbox pattern solve the dual-write problem?
4. A query is fast in staging but slow in production. Which plans, statistics, indexes, and data distributions do you compare?
5. A deadlock appears after a deployment. How do you reproduce it and reduce lock-order conflicts?
6. A money transfer fails halfway through. Which transaction boundary and compensating action are required?
7. A schema change must support old and new application versions. Describe an expand-and-contract migration.
8. A cache returns stale user permissions. What invalidation or TTL strategy prevents an authorization bug?

### Kafka and Event-Driven Systems

1. A consumer processes a payment twice after a rebalance. How do you achieve an effectively-once business outcome?
2. Events arrive out of order. Which key, partitioning, versioning, or domain rule can handle that?
3. Consumer lag grows every afternoon. What metrics distinguish insufficient capacity from a slow dependency?
4. A poison message blocks a partition. How do retries, backoff, dead-letter topics, and replay work together?
5. A producer publishes before its database transaction commits. How do you make publication reliable?
6. A team changes an event field from integer to string. How do you preserve schema compatibility?
7. A consumer must rebuild a read model. How do you reset offsets and verify the result safely?

### Distributed Systems and Resilience

1. A retry storm takes down a dependency. How do timeouts, backoff, jitter, retry budgets, and circuit breakers help?
2. A checkout spans inventory, payment, and shipping. Would you use a distributed transaction or a saga, and why?
3. A cache outage makes every request hit the database. How do you prevent a cache stampede?
4. One tenant consumes all worker capacity. How do you add fairness, quotas, and bulkheads?
5. A region becomes unreachable. Which operations can degrade, and how is recovery tested?
6. A clock-based expiration rule differs across servers. Where do monotonic clocks and server-side timestamps matter?
7. An API must support 100,000 requests per second. What do you measure first, and where might the bottleneck move?

### Security

1. A user changes `/users/123` to view another user. What authorization check is missing?
2. A JWT is valid but should not access an admin endpoint. Which issuer, audience, scope, role, and tenant checks are required?
3. A secret appears in logs and a build artifact. What is your containment and rotation plan?
4. An endpoint accepts a URL and fetches it server-side. How do you prevent SSRF?
5. A file upload is required. How do you protect storage, filenames, size limits, and malware scanning?
6. A dependency has a critical CVE but upgrading may break behavior. How do you assess, mitigate, test, and document the risk?
7. A prompt injection tells an AI assistant to reveal internal documents. Which retrieval filters, tool permissions, output checks, and human controls stop it?

### Testing and Quality

1. A unit test passes but the service fails against PostgreSQL. What should be an integration test instead?
2. A contract change breaks a mobile client weeks later. How do consumer-driven contract tests help?
3. A flaky test only fails in parallel CI. How do you find shared state, timing assumptions, and order dependence?
4. A test suite takes 45 minutes. Which tests do you parallelize, replace, quarantine, or move earlier?
5. A retry feature has many branches. Which failure matrix and property-based tests give confidence?
6. A load test shows good average latency but poor p99. What do you investigate?

### Kubernetes, CI/CD, and Operations

1. A pod is healthy but receives no traffic. Which readiness, Service selector, port, and ingress settings do you inspect?
2. A deployment enters `CrashLoopBackOff`. How do you use events, logs, probes, resource limits, and configuration checks?
3. A migration fails during rollout. How do you keep old instances compatible and recover without data loss?
4. A release causes elevated errors. What telemetry and deployment strategy enable fast rollback?
5. A service is OOM-killed. How do heap settings, container limits, native memory, leaks, and traffic shape affect diagnosis?
6. A downstream dependency has no SLO. How do you set an error budget and alert without paging on every transient error?
7. A build executes an untrusted pull request. How do you protect credentials, runners, artifacts, and deployment permissions?

### AI Application Engineering

1. A RAG assistant gives confident but unsupported answers. How do you improve retrieval, citations, grounding checks, and abstention?
2. Retrieval returns documents from another tenant. Where must tenant filtering happen, and how do you test it?
3. Model latency and cost exceed the budget. Which caching, routing, streaming, context, and model-size changes are safe?
4. A model returns malformed JSON. How do structured-output validation, retries, fallbacks, and human review work?
5. An LLM tool can issue refunds. How do you enforce least privilege, confirmation, idempotency, audit logs, and approval thresholds?
6. A model provider changes behavior after an upgrade. Which golden datasets, evaluation metrics, version pins, and rollout controls detect regression?

## Portfolio Projects

1. **Order platform:** Spring Boot, PostgreSQL, Kafka, outbox, idempotency, OpenTelemetry, Docker, and Kubernetes.
2. **Multi-tenant SaaS:** OIDC, tenant isolation, audit logs, row-level authorization, rate limits, and billing events.
3. **Documentation assistant:** ingestion, embeddings, vector search, citations, evaluation, prompt-injection defenses, and cost dashboard.
4. **Incident lab:** introduce slow queries, dependency failures, duplicate messages, memory pressure, and bad deployments; then diagnose each with telemetry.

## Definition of Done for Every Project

- Automated unit, integration, contract, and failure-path tests.
- API documentation and a short architecture decision record.
- Structured logs, metrics, traces, dashboards, and an SLO.
- Database migration and rollback strategy.
- Authentication, authorization, secret handling, and dependency scanning.
- Container image, CI pipeline, deployment manifest, and rollback runbook.
- Load-test results with p50, p95, p99, throughput, and resource usage.
- README with trade-offs, known limits, and local reproduction steps.

## Suggested 12-Week Plan

| Weeks | Focus | Output |
| --- | --- | --- |
| 1-2 | Java fundamentals and modern features | Expense tracker and tests |
| 3 | Collections, JVM, and concurrency | Concurrent price aggregator |
| 4-5 | Spring Boot REST, validation, and security | Versioned order API |
| 6 | PostgreSQL, JPA, transactions, and migrations | Reliable persistence layer |
| 7 | Kafka, outbox, and idempotent consumers | Event-driven order flow |
| 8 | Resilience and distributed design | Failure-mode test suite |
| 9 | Docker, Kubernetes, and CI/CD | Repeatable deployment |
| 10 | Observability and performance | SLO dashboard and load report |
| 11 | Threat modeling and security hardening | Security tests and runbook |
| 12 | AI feature or incident lab | Portfolio demo and design review |

## How to Practice

For each question, draw the request and data flow, name the failure modes, write one test that would catch the bug, and state one metric that would prove the design works. The strongest answers explain trade-offs rather than naming a framework.

## PDF-to-Code Examples

The repository now includes runnable example files that correspond to the main interview PDF topics:

- Database / SQL interview guide: [examples/01_database_sql/schema.sql](examples/01_database_sql/schema.sql) and [examples/01_database_sql/query_examples.sql](examples/01_database_sql/query_examples.sql)
- Java 8 interview guide: [examples/02_java8/Java8FeaturesDemo.java](examples/02_java8/Java8FeaturesDemo.java)
- Streams / lambda / functional interface guide: [examples/03_java_streams/StreamInterviewExamples.java](examples/03_java_streams/StreamInterviewExamples.java)
- Spring Boot / microservices guide: [examples/04_springboot_microservices/OrderApplication.java](examples/04_springboot_microservices/OrderApplication.java), [examples/04_springboot_microservices/OrderController.java](examples/04_springboot_microservices/OrderController.java), [examples/04_springboot_microservices/OrderService.java](examples/04_springboot_microservices/OrderService.java), and [examples/04_springboot_microservices/application.yml](examples/04_springboot_microservices/application.yml)
- AWS / DevOps architect guide: [examples/05_aws_devops_architect/Dockerfile](examples/05_aws_devops_architect/Dockerfile), [examples/05_aws_devops_architect/deployment.yaml](examples/05_aws_devops_architect/deployment.yaml), and [examples/05_aws_devops_architect/ci_pipeline.yaml](examples/05_aws_devops_architect/ci_pipeline.yaml)
- Senior full-stack Java tech lead guide: [examples/06_tech_lead/TechLeadScenarioDemo.java](examples/06_tech_lead/TechLeadScenarioDemo.java)
- HCL interview mock guide: [examples/07_hcl_mock/HclInterviewPractice.java](examples/07_hcl_mock/HclInterviewPractice.java)

Use the example files as starting points for practice, then adapt them into your own projects and interview answer walkthroughs.