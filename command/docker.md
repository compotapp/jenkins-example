# Запускаем Jenkins контейнер
docker run -d `
--name jenkins `
--restart=on-failure `
-p 8080:8080 `
-p 50000:50000 `
-v jenkins_home:/var/jenkins_home `
-v /var/run/docker.sock:/var/run/docker.sock `
-v /usr/bin/docker:/usr/bin/docker `
jenkins/jenkins:lts-jdk17

# -d - запускает контейнер в фоновом режиме (detached)
# --name jenkins - дает контейнеру имя "jenkins"
# --restart=on-failure - автоматически перезапускать контейнер при падении
# -p 8080:8080 - пробрасывает порт 8080 из контейнера на хост (ваш компьютер)
# -p 50000:50000 - пробрасывает порт для агентов Jenkins
# -v jenkins_home:/var/jenkins_home - создает постоянное хранилище данных
# -v /var/run/docker.sock:/var/run/docker.sock - дает доступ к Docker из контейнера (для сборки Docker образов)
# jenkins/jenkins:lts-jdk17 - образ Jenkins с JDK 17 (LTS = Long Term Support)

# Увеличим лимиты ресурсов для контейнера
docker update jenkins `
--memory 2g `
--memory-swap 4g `
--cpus 2

# Остановить Jenkins
docker stop jenkins

# Запустить Jenkins
docker start jenkins

# Перезапустить Jenkins
docker restart jenkins

# Просмотр логов в реальном времени
docker logs -f jenkins

# Залогиниться в контейнер (для отладки)
docker exec -it jenkins /bin/bash

# Создать резервную копию данных
docker run --rm \
-v jenkins_home:/source \
-v $(pwd):/backup \
alpine tar czf /backup/jenkins_backup_$(date +%Y%m%d).tar.gz -C /source .

# Обновить Jenkins до новой версии
docker stop jenkins
docker rm jenkins
docker pull jenkins/jenkins:lts-jdk17
# Запустите с теми же volume и портами

# Если хотите проверить из контейнера
docker exec -it jenkins /bin/bash
java -version
mvn --version
exit