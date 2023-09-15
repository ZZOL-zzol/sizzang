import React, { useEffect, useState } from "react";
import SearchBar from "./SearchBar";

const { kakao } = window;

const MapPractice = () => {
  const [keyword, setKeyword] = useState("");
  const [map, setMap] = useState(null);

  useEffect(() => {
    const container = document.getElementById("map");
    const options = {
      center: new kakao.maps.LatLng(37.2255, 127.1161),
      level: 3,
    };
    const newMap = new kakao.maps.Map(container, options);
    setMap(newMap);
    const markerPosition = new kakao.maps.LatLng(37.2255, 127.1161);
    const marker = new kakao.maps.Marker({
      position: markerPosition,
    });
    marker.setMap(newMap);
  }, []);

  useEffect(() => {
    if (map && keyword) {
      // 검색어가 입력되면 지도 이동
      searchLocation(keyword);
    }
  }, [map, keyword]);

  const searchLocation = (address) => {
    const geocoder = new kakao.maps.services.Geocoder();
    geocoder.addressSearch(address, function (result, status) {
      if (status === kakao.maps.services.Status.OK) {
        const coords = new kakao.maps.LatLng(result[0].y, result[0].x);
        map.panTo(coords);
      }
    });
  };

  return (
    <div>
      <div>
        <div id="map" style={{ width: "500px", height: "400px" }}></div>
      </div>
      <div id="menu_wrap" className="">
        <div className="option">
          <div>
            <form>
              <div className="flex flex-col w-full bg-white pb-2">
                <SearchBar
                  placeholder="시장, 점포를 입력하세요."
                  setKeyword={setKeyword}
                ></SearchBar>
              </div>
            </form>
          </div>
        </div>
        <hr />
        <ul id="placesList"></ul>
        <div id="pagination"></div>
      </div>
    </div>
  );
};

export default MapPractice;