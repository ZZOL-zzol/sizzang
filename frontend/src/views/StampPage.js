import Header from "../components/common/Header";
import Navbar from "../components/common/Navbar";
import Carousel from "../components/stamp/Carousel";
import { API_URL } from "../lib/constants";
import { useEffect, useState } from "react";
import axios from "axios";

const StampPage = () => {
  const user = JSON.parse(window.localStorage.getItem("User"));
  const [stampCardList, setStampCardList] = useState([]);
  
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
          setStampCardList(res.data.data);
        })
        .catch((err) => console.log(err));

  }, []);



  return (
    <div className="w-full bg-background-fill">
      <Header title="내 스탬프" backButton route="/profile" />
      <div className="flex flex-col h-full pb-[60px] overflow-auto bg-background-fill">
        <div>
          <Carousel region={"서울특별시"} stampCardList={stampCardList} />
        </div>
        <div>
          <Carousel region={"부산광역시"} stampCardList={stampCardList} />
        </div>
        <div>
          <Carousel region={"강원특별자치도"} stampCardList={stampCardList} />
        </div>
      </div>
      <Navbar />
    </div>
  );
};

export default StampPage;
