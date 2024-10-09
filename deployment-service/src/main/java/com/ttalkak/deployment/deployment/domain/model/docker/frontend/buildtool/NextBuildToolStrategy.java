package com.ttalkak.deployment.deployment.domain.model.docker.frontend.buildtool;

public class NextBuildToolStrategy implements BuildToolStrategy {

    @Override
    public String buildFromImage() {
        return "FROM node:18-alpine AS runner\n";
    }

    @Override
    public String copyBuildOutput() {
        return "COPY --from=builder /app/package*.json ./\n" +
                "COPY --from=builder /app/.next ./.next\n" +
                "COPY --from=builder /app/public ./public\n" +
                "RUN npm install --production\n";
    }

    @Override
    public String cmdCommand() {
        return "CMD [\"npm\", \"start\"]";
    }
}
