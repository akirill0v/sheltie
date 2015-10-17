FROM clojure:latest

# Install boot
RUN curl -OL https://github.com/boot-clj/boot/releases/download/2.2.0/boot.sh &&\
    mv boot.sh boot && chmod a+x boot && mv boot /usr/local/bin && BOOT_AS_ROOT=yes boot -h

# Phantomhs v2.0.1-development
RUN curl -OL http://googledrive.com/host/0B1Hg8BeqiY7EZEVqZU93QjQtQTQ/phantomjs && \
    chmod a+x phantomjs && mv phantomjs /usr/local/bin

CMD boot
