FROM node
RUN npm install -g express-gateway
RUN eg gateway create -t basic -d gw -n api-gateway
EXPOSE 80 443
COPY gateway.config.yml gw/config/gateway.config.yml
ENTRYPOINT ["npm", "start", "--prefix", "gw/"]
