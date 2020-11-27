# Use the official image as a parent image.
FROM node:14-alpine as builder

# Copy the rest of your app's source code from your host to your image filesystem.
COPY . .

# Run the command inside your image filesystem.
RUN npm install npm@7

# Add metadata to the image to describe which port the container is listening on at runtime.
EXPOSE 3000

# Run the specified command within the container.
CMD [ "npm", "start" ]

