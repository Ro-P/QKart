# Download selenium-run.sh script
#curl -O https://gitlab.crio.do/crio_bytes/selenium/me_selenium_web_actions/-/raw/master/SeleniumWebActions/selenium-run.sh
#chmod +x selenium-run.sh
# docker installation
sudo apt-get update
sudo apt-get -y install docker.io

# pull selenium and zalenium docker images
sudo docker pull elgalu/selenium
sudo docker pull dosel/zalenium