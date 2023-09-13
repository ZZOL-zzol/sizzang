import React, { useEffect, useState } from "react";
import PageTitle from "../../../components/common/PageTitle";
import ProfileSection from "../../../components/mypage/ProfileSection";
import AccountCard from "../../../components/mypage/account/AccountCard";
import ProfileEditCard from "../../../components/mypage/ProfileEditCard";
import ConsumptionDetail from "./ConsumptionDetail";
import Button from "../../common/Button";
import AccountListCard from "../account/AccountListCard";
import { useNavigate } from "react-router-dom";
import { API_URL } from "../../../lib/constants";
import axios from "axios";

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

const ConsumerMyPage = (props) => {
  const navigate = useNavigate();
  const [openProfileEdit, setOpenProfileEdit] = useState(false);
  const [openAddAccount, setOpenAddAccount] = useState(false);
  const user = JSON.parse(window.localStorage.getItem("User"));
  // const [isDetailButtonClicked, setIsDetailButtonClicked] = useState(false);

  const onAccountCardClick = (value) => {
    navigate("/history", { state: { accountNumber: value } });
  };

  useEffect(() => {
    axios.get(
      `${API_URL}/bank/v1/search/registedAccounts`,
      JSON.stringify({ userId: user.userId }),
      { headers: { "Content-Type": "application/json" } }
    )
    .then(res=>console.log(res))
    .catch(err=>console.log(err));
  }, []);

  return (
    <div className="w-full h-full bg-white p-5">
      {openProfileEdit ? (
        <ProfileEditCard
          setOpenProfileEdit={setOpenProfileEdit}
          user={props.user}
        />
      ) : openAddAccount ? (
        <AccountListCard setOpenAddAccount={setOpenAddAccount} />
      ) : (
        <div className="flex flex-col gap-3">
          <ProfileSection
            setOpenProfileEdit={setOpenProfileEdit}
            user={props.user}
          />
          <div className="flex flex-col gap-5">
            {accountList.map((account, index) => (
              <AccountCard
                account={account}
                key={index}
                onClickEvent={onAccountCardClick}
                type="history"
              />
            ))}
            <div
              className="flex justify-center items-center gap-2 text-outline"
              onClick={() => setOpenAddAccount(true)}
            >
              <svg
                className="fill-outline"
                xmlns="http://www.w3.org/2000/svg"
                height="1em"
                viewBox="0 0 512 512"
              >
                <path d="M256 512A256 256 0 1 0 256 0a256 256 0 1 0 0 512zM232 344V280H168c-13.3 0-24-10.7-24-24s10.7-24 24-24h64V168c0-13.3 10.7-24 24-24s24 10.7 24 24v64h64c13.3 0 24 10.7 24 24s-10.7 24-24 24H280v64c0 13.3-10.7 24-24 24s-24-10.7-24-24z" />
              </svg>
              계좌 간편 등록
            </div>
            <div></div>
            <div>
              {/* <Button
                color="bg-secondary-container"
                innerText="소비관리"
                image="./market.svg"
                type="mypage"
                route='/history'
              /> */}
              <Button
                color="bg-primary-container"
                innerText="스탬프"
                image="./market.svg"
                type="mypage"
                route="/stamp"
              />
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default ConsumerMyPage;
