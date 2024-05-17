FROM node:current-alpine
RUN npm install -g http-server
WORKDIR /app/frontend
COPY package*.json ./
RUN npm install --omit=dev
COPY . .
RUN npm run build
CMD ["http-server", "dist", "-p", "3000"]
