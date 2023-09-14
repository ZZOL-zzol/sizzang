import React, { useEffect, useState } from "react";  
import SearchBar from "./SearchBar";

const { kakao } = window;

const MapPractice=()=>{

    const [keyword, setKeyword] = useState("");

    const onKeywordChange = (e) => {
      console.log(e)
      // setKeyword(e.target.value);
    };

  useEffect(()=>{
    var container = document.getElementById('map');
    var options = {
      center: new kakao.maps.LatLng(37.2255, 127.1161),
      level: 3
    };
    var map = new kakao.maps.Map(container, options);
    var markerPosition  = new kakao.maps.LatLng(37.2255, 127.1161); 
    var marker = new kakao.maps.Marker({
      position: markerPosition
  });
  marker.setMap(map);
    }, [])


    return (
        <div>
             <div>
        	<div id="map" style={{width:"500px", height:"400px"}}></div> 
            </div>
            <div id="menu_wrap" className="">
        <div className="option">
          <div>
            <form>
              <div className="flex flex-col w-full bg-white pb-2">
                <SearchBar palceholder='시장, 점포를 입력하세요.' setKeyword={()=>setKeyword}></SearchBar>
                {/* <input
                type="text"
                value={keyword}
                id="keyword"
                size="15"
                onChange={onKeywordChange}
              /> */}
              </div>
            </form>
          </div>
        </div>
        <hr />
        <ul id="placesList"></ul>
        <div id="pagination"></div>
      </div>

        </div>
       

        
    )
}

export default MapPractice;
 