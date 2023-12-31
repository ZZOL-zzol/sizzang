import axios from "axios";
import { useEffect, useState } from "react";
import Header from "../components/common/Header";
import Loading from "../components/common/Loading";
import Navbar from "../components/common/Navbar";
import SearchBar from "../components/common/SearchBar";
import Tabs from "../components/common/Tabs";
import Carousel from "../components/product/Carousel";
import Category from "../components/product/Category";
import ProductAverageCard from "../components/product/ProductAverageCard";
import ProductCurrentPriceCard from "../components/product/ProductCurrentPriceCard";
import { API_URL, KAMIS_API_URL } from "../lib/constants";



const ProductSearchPage = () => {
  const [productList, setProductList] = useState([]);
  const [selectedPdCode, setSelectedPdCode] = useState(null);
  const [currentView, setCurrentView] = useState(0);
  const [currentCategory, setCurrentCategory] = useState("all");
  const [isLoading, setIsLoading] = useState(true);
  const [keyword, setKeyword] = useState("");
  const [resultList, setResultList] = useState([]);
  const [countyCode, setCountyCode] = useState("");

  const onKeywordChange = (e) => {
    setKeyword(e.target.value);
  };

  const onSearchButtonClick = () => {
    console.log("검색시작");
    setResultList([]);
    for (let i = 0; i < productList.length; i++) {
      if (productList[i].productName.includes(keyword)) {
        setResultList((prev) => [...prev, productList[i]]);
        console.log(resultList);
      }
    }
  };

  const onTabClick = (value) => {
    console.log(value)
    setCurrentView(value)
    if(value === 1){
      setCurrentCategory('all')
      console.log('-오늘의 전체 평균가')
      axios
        .get(`${API_URL}/prtag`)
        .then((res) => {
          setProductList(res.data.data);
          setIsLoading(false);
        })
        .catch((err) => console.log(err));
    }else{
      setCurrentCategory('all')
    }
  }

  const onViewCategoryChange = (category, view) => {
    //1101:서울, 2100:부산, 2200:대구, 2401:광주, 2501:대전
    setCurrentCategory(category)
    setIsLoading(true);
    setProductList([]);
    console.log(category)
    setIsLoading(true);
    setProductList([]);

    if (view === 0 && category === "all") {
      console.log('- 오늘의 전체 시세')
      axios
        .get(
          `http://cors-anywhere.herokuapp.com/${KAMIS_API_URL}/service/price/xml.do`,
          {
            params: {
              p_cert_key: "9ec7751a-6865-4116-98d5-181afba8c407",
              p_cert_id: "222",
              p_returntype: "json",
            },
          }
        )
        .then((res) => {
          setProductList(res.data.price);
          setIsLoading(false);
          console.log(res);
        })
        .catch((err) => console.log(err));
    } else if (view === 0 && category !== "all") {
      console.log('- 오늘의 지역별 시세')
      axios
        .get(
          `http://cors-anywhere.herokuapp.com/${KAMIS_API_URL}/service/price/xml.do?action=ItemInfo`,
          {
            params: {
              p_cert_key: "9ec7751a-6865-4116-98d5-181afba8c407",
              p_cert_id: "222",
              p_returntype: "json",
              p_countycode: category,
            },
          }
        )
        .then((res) => {
          setProductList(res.data.price);
          setIsLoading(false);
          console.log(res);
        })
        .catch((err) => console.log(err));
    } else if (view === 1 && category === "all") {
      console.log('- 오늘의 전체 평균가')
      axios
        .get(`${API_URL}/prtag`)
        .then((res) => {
          setProductList(res.data.data);
          setIsLoading(false);
        })
        .catch((err) => console.log(err));
    }else{
      console.log('- 오늘의 품목별 평균가')
      axios
        .get(`${API_URL}/prtag/${category}`)
        .then((res) => {
          setProductList(res.data.data);
          setIsLoading(false);
        })
        .catch((err) => console.log(err))
    }
  };

  useEffect(() => {
    axios
        .get(
          `http://cors-anywhere.herokuapp.com/${KAMIS_API_URL}/service/price/xml.do`,
          {
            params: {
              p_cert_key: "9ec7751a-6865-4116-98d5-181afba8c407",
              p_cert_id: "222",
              p_returntype: "json",
            },
          }
        )
        .then((res) => {
          setProductList(res.data.price);
          setIsLoading(false);
          console.log(res);
        })
        .catch((err) => console.log(err));
  }, [])
  


  //1101:서울, 2100:부산, 2200:대구, 2401:광주, 2501:대전
  console.log("리렌더")
  return (
    <div className="App flex flex-col text-3xl h-full w-full bg-background-fill py-16">
      <Header title="상품 시세" basketButton />
      <div className="flex flex-col h-full">
        <div className="bg-white w-full h-16">
          <SearchBar
            placeholder="상품을 검색하세요."
            keyword={keyword}
            onChangeEvent={onKeywordChange}
            onClickEvent={onSearchButtonClick}
          />
        </div>
        {selectedPdCode !== null ? null : (
          <Category
            currentView={currentView}
            currentCategory={currentCategory}
            onChangeEvent={onViewCategoryChange}
          ></Category>
        )}
        <div className="bg-white">
          <Tabs
            tab1="오늘의 시세"
            tab2="상품 평균가"
            onTabClick={onTabClick}
          />
        </div>
        <div className="bg-white pt-5">
          <div className="flex font-semibold text-xl justify-center items-center">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              height="1em"
              viewBox="0 0 448 512"
              className="fill-myerror"
            >
              <path d="M159.3 5.4c7.8-7.3 19.9-7.2 27.7 .1c27.6 25.9 53.5 53.8 77.7 84c11-14.4 23.5-30.1 37-42.9c7.9-7.4 20.1-7.4 28 .1c34.6 33 63.9 76.6 84.5 118c20.3 40.8 33.8 82.5 33.8 111.9C448 404.2 348.2 512 224 512C98.4 512 0 404.1 0 276.5c0-38.4 17.8-85.3 45.4-131.7C73.3 97.7 112.7 48.6 159.3 5.4zM225.7 416c25.3 0 47.7-7 68.8-21c42.1-29.4 53.4-88.2 28.1-134.4c-4.5-9-16-9.6-22.5-2l-25.2 29.3c-6.6 7.6-18.5 7.4-24.7-.5c-16.5-21-46-58.5-62.8-79.8c-6.3-8-18.3-8.1-24.7-.1c-33.8 42.5-50.8 69.3-50.8 99.4C112 375.4 162.6 416 225.7 416z" />
            </svg>
            오늘의 핫딜
          </div>
          <Carousel></Carousel>
        </div>
        <div className="h-auto overflow-scroll">
          {isLoading ? (
            <div className="w-full pt-10 mx-auto">
              <Loading />
            </div>
          ) : currentView === 0 && resultList.length === 0 ? (
            productList.map((product, index) => (
              <ProductCurrentPriceCard
                product={product}
                key={index}
                setSelectedPdCode={() => setSelectedPdCode(product.pdCode)}
              />
            ))
          ) : currentView === 0 && resultList.length > 0 ? (
            resultList.map((product, index) => (
              <ProductCurrentPriceCard
                product={product}
                key={index}
                setSelectedPdCode={() => setSelectedPdCode(product.pdCode)}
              />
            ))
          ) : currentView === 1 && resultList.length === 0 ? (
            productList.map((product, index) => (
              <ProductAverageCard
                product={product}
                key={index}
                setSelectedPdCode={() => setSelectedPdCode(product.tagCode)}
              />
            ))
          ) : null}
        </div>
      </div>
      <Navbar />
    </div>
  );
};

export default ProductSearchPage;
