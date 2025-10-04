def call() {
    // Wait up to 2 minutes for SonarQube to send the webhook result
    timeout(time: 2, unit: "MINUTES") {
        def qualityGate = waitForQualityGate abortPipeline: true

        echo "SonarQube Quality Gate status: ${qualityGate.status}"

        if (qualityGate.status != 'OK') {
            error "❌ Quality Gate failed: ${qualityGate.status}"
        } else {
            echo "✅ Quality Gate passed successfully."
        }
    }
}
