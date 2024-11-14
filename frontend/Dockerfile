FROM node:20
WORKDIR /front
COPY .. .
RUN npm install
RUN npm run build

CMD ["npm", "run", "startHeadless"]
EXPOSE 3000


