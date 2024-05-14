#!/bin/bash

# Espera até que o serviço MySQL esteja pronto
until docker-compose exec -T db mysqladmin ping -h"localhost" --silent; do
    echo 'Aguardando pelo MySQL...'
    sleep 1
done

# Executa o comando MySQL após o serviço estar pronto
docker-compose exec -T db mysql -uroot -p"root" -e "SEU_COMANDO_AQUI"
