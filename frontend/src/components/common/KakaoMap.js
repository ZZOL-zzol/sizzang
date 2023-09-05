import { useEffect } from "react";

const { kakao } = window;

const KakaoMap = () => {
  useEffect(() => {
    const container = document.getElementById("map");
    const options = {
      center: new kakao.maps.LatLng(33.450701, 126.570667),
      level: 3,
    };
    const map = new kakao.maps.Map(container, options);

    const markerPosition = new kakao.maps.LatLng(
        33.450701, 126.570667
    );
    const marker = new kakao.maps.Marker({
      position: markerPosition,
    });
    marker.setMap(map);
  }, []);

  return <div id="map" className="w-[500px] h-[500px]"></div>;
};

export default KakaoMap;
