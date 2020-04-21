# Code Challenge

## How to run

```
docker-compose up
```

### Docker-compose

* **tweets-reader service:** Builds image from Dockerfile, exposes and connects it to internal network. It uses H2 embedded database.
* **auth-proxy service:** Builds image from Dockerfile, exposes and redirects connections to tweets-reader service. **The parameterized implementation** allows redirect auth-proxy to tweets-reader endpoint inside the container and internal network.
* **Internat network:** It allows and manages communication between containers.
