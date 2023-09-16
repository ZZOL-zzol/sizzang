import React, { useEffect, useState } from "react";
import { markerdata } from "./markerData";

const { kakao } = window; 

export default function Map(props) {
  // const [mkLatitude, setMkLatitude]=useState(37.2249262);
  // const [mkLongtitude, setMkLongtitude]=useState(127.116550);
  // const [mkName, setMkName] = useState('신한은행 블루캠퍼스');

  useEffect(() => {
      // console.log(props.selectedMarket)
      // setMkLatitude(props.selectedMarket.mkLatitude)
      // setMkLongtitude(props.selectedMarket.mkLongtitude)
      // setMkName(props.selectedMarket.mkName)
    mapscript();
  }, [props.mkLatitude]);

  const mapscript = () => {
    let container = document.getElementById("map");
    let options = {
      center: new kakao.maps.LatLng(props.mkLatitude, props.mkLongtitude),
      level: 5,
    };

    const map = new kakao.maps.Map(container, options);
    
    // markerdata.forEach((el) => {
    //   // 마커를 생성합니다
    //   new kakao.maps.Marker({
    //     //마커가 표시 될 지도
    //     map: map,
    //     //마커가 표시 될 위치
    //     position: new kakao.maps.LatLng(el.lat, el.lng),
    //     //마커에 hover시 나타날 title
    //     title: el.title,
    //   });
    // });

    new kakao.maps.Marker({
      map : map,
      position : new kakao.maps.LatLng(props.mkLatitude, props.mkLongtitude),
      title : props.mkName
    })
  };

  return <div id="map" className="h-[350px]"></div>;
}