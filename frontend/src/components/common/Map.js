import React, { useEffect, useState } from "react";

const { kakao } = window; 

export default function Map(props) {
  useEffect(() => {
    mapscript();
  }, [props.mkLatitude]);

  const mapscript = () => {
    let container = document.getElementById("map");
    let options = {
      center: new kakao.maps.LatLng(props.mkLatitude, props.mkLongtitude),
      level: 5,
    };

    const map = new kakao.maps.Map(container, options);

    new kakao.maps.Marker({
      map : map,
      position : new kakao.maps.LatLng(props.mkLatitude, props.mkLongtitude),
      title : props.mkName
    })
  };

  return <div id="map" className="h-[350px]"></div>;
}