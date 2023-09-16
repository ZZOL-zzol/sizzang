import Header from "../components/common/Header";
import Navbar from "../components/common/Navbar";
import Carousel from "../components/stamp/Carousel";
import { API_URL } from "../lib/constants";
import { useEffect, useState } from "react";
import axios from "axios";

let selectedRegion = '서울'

const StampPage = () => {
  const user = JSON.parse(window.localStorage.getItem("User"));
  const [stampCardAll, setStampCardAll] = useState([]);
  const [stampCardList, setStampCardList] = useState([]);
  const selectList = [
    {value : "서울특별시", data : "서울"},
    {value : "경기도", data : "경기"},
    {value : "충청도", data : "충청"},
    {value : "경상도", data : "경상"},
    {value : "전라도", data : "전라"},
    {value : "제주도", data : "제주"}
  ];
  // const [selectedRegion, setSelectedRegion] = useState("");
  
  const handleSelect =   (e) => {
    setStampCardList([])
    console.log(e)
    selectedRegion = e.target.value
    console.log(selectedRegion)
    stampCardAll.map(stamp => String(stamp.regionNameFirst).includes(selectedRegion)? setStampCardList(prev=>[...prev,stamp]):null)
  };
  
  useEffect(() => {
    axios
      .post(
        `${API_URL}/stamp/getAll`,
        JSON.stringify({ userCode: 1 }),
        {
          headers: { "Content-Type": "application/json" },
        }
      )
      .then((res) => {
        setStampCardAll(res.data.data); //전체리스트
        
      })
      .catch((err) => console.log(err));
  }, []);

  return (
    <div className="w-full bg-background-fill">
      <Header title="내 스탬프" backButton route="/profile" />
      <div className="flex flex-col h-full py-16 overflow-auto bg-background-fill justify-center">
        <div className="flex flex-col gap-3 w-full justify-center items-center bg-white p-5">
          <select
            onChange={handleSelect}
            value={selectedRegion}
            className="w-1/3 rounded-full px-2 py-1 bg-background-fill"
          >
            {selectList.map((item) => (
              <option value={item.data} key={item.data} className="w-1/3">
                {item.value}
              </option>
            ))}
          </select>
          <div>
            <Carousel region={selectedRegion} stampCardList={stampCardList} />
          </div>
        </div>
      </div>
      <Navbar />
    </div>
  );
};

export default StampPage;
