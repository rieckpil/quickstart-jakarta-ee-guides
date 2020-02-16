# Deploy a Jakarta EE application to a local Kubernetes Cluster

Install a local Kubernetes Cluster (with a single node) on your machine:

* For Linux: [MicroK8s](https://microk8s.io/docs/)
* For Mac/Windows: A local Kubernetes Cluster can be enabled with the latest versions of Docker for Windows/Mac

Steps to run this project:

1. Start your Docker daemon and ensure your local Kubernetes Cluster is up- and running
2. Create a local registry with `docker run -d -p 5000:5000 --restart=always --name registry registry:2`
3. Execute `./buildAndRun.sh` (Linux/MacOs) or `buildAndRun.bat` (Windows)
4. Execute `kubectl apply -f deployment.yml` to deploy the application to Kubernetes
5. Wait until Open Liberty is up- and running (e.g. use `kubectl get pods`)
6. Visit http://localhost:31000/resources/sample

