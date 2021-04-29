# Before deploying, make sure to import the GPG key

deploy:
	./mvnw -Dmaven.repo.local=.m2/repository --batch-mode --errors --show-version -s .mvn/settings.xml clean deploy -DskipTests -DperformRelease=true
