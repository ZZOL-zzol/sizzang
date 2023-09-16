import Header from "../components/common/Header";
import Navbar from "../components/common/Navbar";
import Carousel from "../components/stamp/Carousel";
import { API_URL } from "../lib/constants";
import { useEffect, useState } from "react";
import axios from "axios";

const StampPage = () => {
  const user = JSON.parse(window.localStorage.getItem("User"));
  const [stampCardAll, setStampCardAll] = useState([]);
  const [stampCardList, setStampCardList] = useState([]);
  const selectList = ["서울특별시", "경기도", "충청도", "경상도", "전라도", "제주도"];
  const [selectedRegion, setSelectedRegion] = useState("");
  
  const handleSelect = (e) => {
    setSelectedRegion(e.target.value);
  };

  useEffect(() => {
    axios
        .post(
          `${API_URL}/stamp/getAll`,
          JSON.stringify({userCode : user.userCode,}),
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
      <div className="flex flex-col h-full pb-[60px] overflow-auto bg-background-fill">
        <select onChange={handleSelect} value={selectedRegion}>
        {selectList.map((item) => (
            <option value={item} key={item}>
              {item}
            </option>
          ))}
        </select>
        <div>
            <Carousel region={selectedRegion} stampCardList={stampCardList} />
          </div>
      </div>
      <Navbar />
    </div>
  );
};

export default StampPage;

// 원래 리턴문
// return (
//   <div className="w-full bg-background-fill">
//     <Header title="내 스탬프" backButton route="/profile" />
//     <div className="flex flex-col h-full pb-[60px] overflow-auto bg-background-fill">
//       <div>
//         <Carousel region={"서울특별시"} stampCardList={stampCardList} />
//       </div>
//       <div>
//         <Carousel region={"부산광역시"} stampCardList={stampCardList} />
//       </div>
//       <div>
//         <Carousel region={"강원특별자치도"} stampCardList={stampCardList} />
//       </div>
//     </div>
//     <Navbar />
//   </div>
// );