import MarketStoreCard from "../common/MarketStoreCard";
import { useLocation } from "react-router-dom";
import SmallButton from "../common/SmallButton";

const ProductCard = (props) => {
  const location = useLocation();
  const currentPage = location.pathname.split("/")[1];

  const addBasket = (value) => {
    window.localStorage.setItem(props.store.stName, {stName: "네네치킨",
    stAddress: "관악구 봉천로 466",
    stPhone: '010-6664-9510',
    productList: [
      {
        pdCode: 1,
        pcCode: 1,
        stCode: "",

        mkCode: "",
        scCode: "",
        pdName: "옛날통닭",
        pdCost: 50000,
        pdIntro: "파닭파닭",
        count: 1,
      }],})
  }
  return (
    <div className="card-body p-3 justify-between border-b-2 bg-white border-outline-container">
      <div className="gap-0 flex justify-between">
        <div className="flex flex-col">
          <div className="card-title text-base">{props.product.pdName}</div>
          <div className="text-base text-outline">{props.product.pdIntro}</div>
        </div>
        {/* <span className="text-sm text-left">{props.product.pdCost}</span> */}
        {props.type === "basket" ? (
          <div className="flex">
            <button className="btn btn-xs btn-circle bg-outline-container">
              -
            </button>
            <div className='mx-1'>{props.product.count}</div>
            <button className="btn btn-xs btn-circle bg-myprimary">
              +
            </button>
          </div>
        ) : (
          <SmallButton color="bg-primary-container" innerText="담기" onClick={()=>addBasket(props.product)}/>
        )}
      </div>

      <div className="flex justify-end items-center">
        <div className="mr-2">{props.product.pdCost}원</div>
        {props.type === "basket" ? null : (
          <div className="text-sm text-myerror">▲1000</div>
        )}
      </div>
    </div>
  );
};

export default ProductCard;
