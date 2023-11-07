FROM openjdk:8
EXPOSE 8089
ADD target/gestion-station-ski-1.0.jar sghaierchahir-5sae5-g1-station-ski
ENTRYPOINT ["java","-jar","/sghaierchahir-5sae5-g1-station-ski"]
