FROM node:18-alpine

WORKDIR /usr/src/frontend                     

COPY package*.json ./
RUN npm install --silent

COPY . .

EXPOSE 3000

CMD ["npm", "start"]