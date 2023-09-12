import Header from "../components/common/Header";
import Navbar from "../components/common/Navbar";
import Carousel from "../components/stamp/Carousel";

const stampCardList = [{}, {}, {}, {}];

const StampPage = () => {
  return (
    <div className="w-full bg-background-fill">
      <Header title="내 스탬프" backButton route="/profile" />
      <div className="flex flex-col h-full pb-[60px] overflow-auto bg-background-fill">
        <div>
          <div className="text-xl font-bold">서울시</div>
          <div className="text-base text-outline">1/10</div>
          <Carousel stampCardList={stampCardList} />
        </div>
        <div>
          <div className="text-xl font-bold">부산시</div>
          <div className="text-base text-outline">1/10</div>
          <Carousel stampCardList={stampCardList} />
        </div>
        <div>
          <div className="text-xl font-bold">경기도</div>
          <div className="text-base text-outline">1/10</div>
          <Carousel stampCardList={stampCardList} />
        </div>
      </div>
      <Navbar />
    </div>
  );
};

export default StampPage;
