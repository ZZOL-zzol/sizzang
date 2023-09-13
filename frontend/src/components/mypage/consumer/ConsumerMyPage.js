import React, { useState } from "react";
import PageTitle from "../../../components/common/PageTitle";
import ProfileSection from "../../../components/mypage/ProfileSection";
import AccountCard from "../../../components/mypage/AccountCard";
import ProfileEditCard from "../../../components/mypage/ProfileEditCard";
import ConsumptionDetail from "./ConsumptionDetail";
import Button from "../../common/Button";
import AccountListCard from "../AccountListCard";

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

const ConsumerMyPage = () => {
  const [openProfileEdit, setOpenProfileEdit] = useState(false);
  const [openAddAccount, setOpenAddAccount] = useState(false);

  // const [isDetailButtonClicked, setIsDetailButtonClicked] = useState(false);

  return (
    <div className="w-full bg-white p-5">
      {openProfileEdit ? (
        <ProfileEditCard setOpenProfileEdit={setOpenProfileEdit}/>
      ) : openAddAccount? <AccountListCard setOpenAddAccount={setOpenAddAccount}/>:(
        <div className="flex flex-col gap-3">
          <ProfileSection setOpenProfileEdit={setOpenProfileEdit} />
          <div className="flex flex-col gap-5">
            {accountList.map((account, index) => (
              <AccountCard account={account}  key={index}/>
            ))}
            <div className="flex justify-center items-center gap-2 text-outline" onClick={()=>setOpenAddAccount(true)}>
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
              <Button
                color="bg-secondary-container"
                innerText="소비관리"
                image="./market.svg"
                type="mypage"
                route='/history'
              />
              <Button
                color="bg-primary-container"
                innerText="스탬프"
                image="./market.svg"
                type="mypage"
                route= '/stamp'
              />
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default ConsumerMyPage;
