import ProductCard from "../components/store/ProductCard";
import Header from "../components/common/Header";
import { useEffect, useState } from "react";
import AccountCard from "../components/basket/AccountCard";
import axios from "axios";
import { API_URL } from "../lib/constants";
import { useSelector } from "react-redux";

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
    accountNumber: "123-456-321",
    accountName: "차차야그만방해해계좌",
    accountBalance: 200000,
  },
];

const BasketPage = () => {
  const [selectedAccountNumber, setSelectedAccountNumber] = useState("");
  const [showAccount, setShowAccount] = useState(false);
  const [basketStore, setBasketStore] = useState({});
  const [basketProductList, setBasketProductList] = useState([]);
  const [basketTotalCost, setBasketTotalCost] = useState(0);
  const [myAccountList, setMyAccountList] = useState([]);
  const basketCount = useSelector((state) => state.basketCount.value);
  const user = JSON.parse(window.localStorage.getItem("User"));

  useEffect(() => {
    const tmpBasketStore = JSON.parse(
      window.localStorage.getItem("BasketStore")
    );
    const tmpBasketProductList = JSON.parse(
      window.localStorage.getItem("BasketProductList")
    );
    if (tmpBasketStore) setBasketStore(tmpBasketStore);
    if (tmpBasketProductList) {
      setBasketProductList(tmpBasketProductList);

      for (let i = 0; i < tmpBasketProductList.length; i++) {
        setBasketTotalCost(
          (prev) =>
            prev +
            tmpBasketProductList[i].pdCost * tmpBasketProductList[i].count
        );
      }
    }

    axios
      .post(
        `${API_URL}/bank/v1/search/registedAccounts`,
        JSON.stringify({ userId: user.userId }),
        { headers: { "Content-Type": "application/json" } }
      )
      .then((res) => {
        console.log(res.data);
        setMyAccountList(res.data);
      })
      .catch((err) => console.log(err));
  }, []);

  const onAccountClick = (value) => {
    console.log(value);
    setSelectedAccountNumber(value);
  };

  const onPayButtonClick = () => {
    if (!showAccount) {
      setShowAccount(true);
    } else {
      console.log(basketStore);
      console.log(basketProductList);

      const purchaseData = [];
      basketProductList.map((product) =>
        purchaseData.push({
          puCnt: product.count,
          prName: product.pdName + "x" + product.count,
          prPrice: product.pdCost * product.count,
        })
      );

      axios
        .get(`${API_URL}/store/${basketStore.stCode}`)
        .then((res) => {
          const data = {
            myAccountNumber: selectedAccountNumber,
            // myMsg:
            //   basketProductList[0].pdName +
            //   "x" +
            //   basketProductList[0].count +
            //   " 외 " +
            //   (basketCount - basketProductList[0].count) +
            //   "건",
            myMsg: basketStore.stName,
            oppenentBankCode: "088",
            opponentAccountNumber: res.data.data.stAccount,
            transactionMsg: user.userName,
            depositAmount: basketTotalCost,
          };
          console.log(data);
          axios
            .post(`${API_URL}/bank/v1/transfer/krw`, JSON.stringify(data), {
              headers: { "Content-Type": "application/json" },
            })
            .then((res) => {
              console.log(res);

              axios.post(
                `${API_URL}/purchase/regist`,
                JSON.stringify({
                  stCode: basketStore.stCode,
                  puCost: basketTotalCost,
                  accountNumber: selectedAccountNumber,
                  details: purchaseData,
                }),
                {
                  headers: { "Content-Type": "application/json" },
                }
              );
            })
            .then((res) => {
              console.log(res);
              window.localStorage.removeItem("BasketProductList");
              window.localStorage.removeItem("BasketStore");
              setBasketProductList([]);
              setBasketStore({});
              setBasketTotalCost(0);
            })
            .catch((err) => console.log(err));
        })
        .catch((err) => console.log(err));
    }
  };

  return (
    <div className="w-full h-full bg-background-fill">
      <Header
        title="장바구니"
        backButton
        route="javascript:window.history.back()"
      />
      <div className="flex flex-col w-full">
        {basketStore.stName ? (
          <div className="w-full">
            <div className="w-full p-5 bg-white mb-3 flex flex-col items-start">
              <div className="font-semibold">{basketStore.stName}</div>
              <div className="text-sm">{basketStore.stAddress}</div>
              <div className="text-sm">{basketStore.stPhone}</div>
            </div>

            {basketProductList.map((product, index) => (
              <ProductCard
                type="basket"
                basketProductList={basketProductList}
                product={product}
                basketStore={basketStore}
                setBasketProductList={setBasketProductList}
                setBasketStore={setBasketStore}
                setBasketTotalCost={setBasketTotalCost}
                index={index}
              />
            ))}
          </div>
        ) : (
          <div className="w-full p-5 bg-white mb-3 flex flex-col items-center gap-2">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              height="3em"
              viewBox="-20 -20 600 550"
              className="fill-white stroke-[20px] stroke-surface"
            >
              <path d="M0 24C0 10.7 10.7 0 24 0H69.5c22 0 41.5 12.8 50.6 32h411c26.3 0 45.5 25 38.6 50.4l-41 152.3c-8.5 31.4-37 53.3-69.5 53.3H170.7l5.4 28.5c2.2 11.3 12.1 19.5 23.6 19.5H488c13.3 0 24 10.7 24 24s-10.7 24-24 24H199.7c-34.6 0-64.3-24.6-70.7-58.5L77.4 54.5c-.7-3.8-4-6.5-7.9-6.5H24C10.7 48 0 37.3 0 24zM128 464a48 48 0 1 1 96 0 48 48 0 1 1 -96 0zm336-48a48 48 0 1 1 0 96 48 48 0 1 1 0-96z" />
            </svg>
            장바구니가 비어있습니다.
          </div>
        )}
        {basketProductList ? (
          <div className="flex flex-col items-end justify-center bg-white h-[50px] px-5 text-lg">
            {basketTotalCost.toLocaleString()}원
          </div>
        ) : null}
      </div>

      <div
        className={ basketProductList.length >0?
          showAccount
            ? "fixed w-full flex flex-col bg-white bottom-0 items-center justify-center px-5 py-5 gap-5 rounded-t-[20px]"
            : "fixed w-full flex flex-col bg-white bottom-0 items-center justify-center px-5 py-5 gap-5"
            : ''
        }
      >
        {showAccount ? (
          <div className="flex self-end" onClick={() => setShowAccount(false)}>
            <svg
              xmlns="http://www.w3.org/2000/svg"
              height="1.5em"
              viewBox="0 0 384 512"
            >
              <path d="M342.6 150.6c12.5-12.5 12.5-32.8 0-45.3s-32.8-12.5-45.3 0L192 210.7 86.6 105.4c-12.5-12.5-32.8-12.5-45.3 0s-12.5 32.8 0 45.3L146.7 256 41.4 361.4c-12.5 12.5-12.5 32.8 0 45.3s32.8 12.5 45.3 0L192 301.3 297.4 406.6c12.5 12.5 32.8 12.5 45.3 0s12.5-32.8 0-45.3L237.3 256 342.6 150.6z" />
            </svg>
          </div>
        ) : null}
        {showAccount
          ? myAccountList.map((account) => (
              <AccountCard
                account={account.accountList[0]}
                selectedAccountNumber={selectedAccountNumber}
                onClickEvent={() =>
                  onAccountClick(account.accountList[0].accountNumber)
                }
                type="pay"
              />
            ))
          : null}
        {basketProductList.length >0?<button
          className="btn h-[40px] w-full bg-myprimary text-xl text-white"
          onClick={() => onPayButtonClick()}
        >
          {showAccount ? "결제하기" : "바로 결제"}
        </button> : null }
        
      </div>
    </div>
  );
};

export default BasketPage;
