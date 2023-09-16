import React, { useEffect, useState } from "react";
import SearchBar from "./SearchBar";
import Map from "./Map";

const { kakao } = window;
const MapSection = (props) => {
  const [map, setMap] = useState(null);

  // useEffect(() => {
  //   const container = document.getElementById("map");
  //   const options = {
  //     center: new kakao.maps.LatLng(37.2255, 127.1161),
  //     level: 3,
  //   };
  //   const newMap = new kakao.maps.Map(container, options);
  //   setMap(newMap);
  //   const markerPosition = new kakao.maps.LatLng(37.2255, 127.1161);
  //   const marker = new kakao.maps.Marker({
  //     position: markerPosition,
  //   });
  //   marker.setMap(newMap);
  // }, []);

  // useEffect(() => {
  // // 장소 검색 객체를 생성
  // const ps = new kakao.maps.services.Places();

  // // 키워드로 장소를 검색
  // ps.keywordSearch('입력 값', placesSearchCB);

  // // 키워드 검색 완료 시 호출되는 콜백함수
  // function placesSearchCB(data, status, pagination) {
  //   if (status === kakao.maps.services.Status.OK) {

  //     // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
  //     // LatLngBounds 객체에 좌표를 추가
  //     let bounds = new kakao.maps.LatLngBounds();

  //     for (let i = 0; i < data.length; i++) {
  //       displayMarker(data[i]);
  //       bounds.extend(new kakao.maps.LatLng(data[i].y, data[i].x));
  //     }

  //     // 검색된 장소 위치를 기준으로 지도 범위를 재설정
  //     map.setBounds(bounds);
  //   }
  // }

  //   function displayMarker(place) {

  //     // 마커를 생성하고 지도에 표시
  //     let marker = new kakao.maps.Marker({
  //         map: map,
  //         position: new kakao.maps.LatLng(place.y, place.x)
  //     });

  //     // 마커에 클릭이벤트를 등록
  //     kakao.maps.event.addListener(marker, 'click', function() {
  //         // 마커를 클릭하면 장소명이 인포윈도우에 표출
  //         infowindow.setContent('<div style="padding:5px;font-size:12px;">' + place.place_name + '</div>');
  //         infowindow.open(map, marker);
  //     });
  // }
  // }, [map, props.keyword]);

  // const searchLocation = (address) => {
  //   const geocoder = new kakao.maps.services.Geocoder();
  //   geocoder.addressSearch(address, function (result, status) {
  //     if (status === kakao.maps.services.Status.OK) {
  //       const coords = new kakao.maps.LatLng(result[0].y, result[0].x);
  //       map.panTo(coords);
  //     }
  //   });
  // };
  const onKeywordChange = (e) => {
    console.log("1");
    props.setKeyword(e.target.value);
  };

  return (
    <div className="w-full h-full flex flex-col">
      {/* <div>
          <div id="map" style={{height: "350px" }}></div>
        </div> */}
      <Map
        mkLatitude={props.mkLatitude}
        mkLongtitude={props.mkLongtitude}
        mkName={props.mkName}
      />
      <div id="menu_wrap" className="">
        <div className="option">
          <div>
            <div className="flex flex-col w-full bg-white pb-2">
              <SearchBar
                placeholder="시장, 점포를 입력하세요."
                keyword={props.keyword}
                onChangeEvent={onKeywordChange}
                onClickEvent={props.onSearchButtonClick}
              ></SearchBar>
            </div>
          </div>
        </div>
        <hr />
      </div>
    </div>
  );
};

export default MapSection;
