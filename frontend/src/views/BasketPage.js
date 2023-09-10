import ProductCard from "../components/store/ProductCard";
import Header from "../components/common/Header";
import { useEffect, useState } from "react";
import AccountCard from "../components/basket/AccountCard";

// const basketList = [
//   {
//     stName: "네네치킨",
//     stAddress: "관악구 봉천로 466",
//     stPhone: "010-6664-9510",
//     productList: [
//       {
//         pdCode: 1,
//         pcCode: 1,
//         stCode: "",

//         mkCode: "",
//         scCode: "",
//         pdName: "옛날통닭",
//         pdCost: 50000,
//         pdIntro: "파닭파닭",
//         count: 1,
//       },
//       {
//         pdCode: 1,
//         pcCode: 1,
//         stCode: "",

//         mkCode: "",
//         scCode: "",
//         pdName: "옛날통닭",
//         pdCost: 50000,
//         pdIntro: "파닭파닭",
//         count: 1,
//       },
//       {
//         pdCode: 1,
//         pcCode: 1,
//         stCode: "",

//         mkCode: "",
//         scCode: "",
//         pdName: "옛날통닭",
//         pdCost: 50000,
//         pdIntro: "파닭파닭",
//         count: 1,
//       },
//     ],
//   },
// ];

const accountList = [
  {
    accountCode: 1,
    accountHolder: "차차아버님",
    accountNumber: "123-456-789",
    accountName: "차차야그만방해해계좌",
    accountBalance: 200000,
  },
  {
    accountCode: 1,
    accountHolder: "차차아버님",
    accountNumber: "123-456-789",
    accountName: "차차야그만방해해계좌",
    accountBalance: 200000,
  },
];

const BasketPage = () => {
  const tmp = window.localStorage.getItem("basket");
  const [showAccount, setShowAccount] = useState(false);
  const onPayButtonClick = () => {
    if (!showAccount) {
      setShowAccount(true);
    } else {
      //결제를 어떤식으로 할건지...
      //여기에 들어가야한다요
      //신한 이체 api 사용 예정
    }
  };
  const [basketList, setBasketList] = useState({});
  useEffect(() => {
    const basket = window.localStorage.getItem("basket");
    if (basket) setBasketList(basket);
  }, []);

  return (
    <div className="w-full h-full bg-background-fill">
      <Header title="장바구니" backButton />
      <div className="flex flex-col w-full">
        {basketList.productList ? (
          basketList.productList.map((store) => (
            <div className="w-full">
              <div className="w-full p-5 bg-white mb-3 flex flex-col items-start">
                <div className="font-semibold">{store.stName}</div>
                <div className="text-sm">{store.stAddress}</div>
                <div className="text-sm">{store.stPhone}</div>
              </div>

              {store.productList.map((product) => (
                <ProductCard product={product} type="basket" />
              ))}
            </div>
          ))
        ) : (
          <div>장바구니가 비어있습니다.</div>
        )}
        {basketList.productList ? (
          <div className="flex flex-col items-end justify-center bg-white h-[50px] px-5 text-lg">
            총 100,000원
          </div>
        ) : null}
      </div>

      <div className="fixed w-full flex flex-col bg-white bottom-0 items-center justify-center px-5 py-7 gap-2">
        {showAccount
          ? accountList.map((account) => (
              <AccountCard account={account} setShowAccount={setShowAccount} type='pay'/>
            ))
          : null}

        <button
          className="btn h-[40px] w-full bg-myprimary text-xl text-white"
          onClick={() => onPayButtonClick()}
        >
          {showAccount ? "결제하기" : "바로 결제"}
        </button>
      </div>
    </div>
  );
};

export default BasketPage;
