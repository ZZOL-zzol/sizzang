import React, { useEffect, useState } from "react";
import ProfileSection from "../../../components/mypage/ProfileSection";
import AccountCard from "../../../components/mypage/account/AccountCard";
import ProfileEditCard from "../../../components/mypage/ProfileEditCard";
import Button from "../../common/Button";
import AccountListCard from "../account/AccountListCard";
import { useNavigate } from "react-router-dom";
import { API_URL } from "../../../lib/constants";
import axios from "axios";

const ConsumerMyPage = (props) => {
  const navigate = useNavigate();
  const [openProfileEdit, setOpenProfileEdit] = useState(false);
  const [openAddAccount, setOpenAddAccount] = useState(false);
  const user = JSON.parse(window.localStorage.getItem("User"));
  // const [isDetailButtonClicked, setIsDetailButtonClicked] = useState(false);
  const [accountList, setAccountList] = useState([]);

  const onAccountCardClick = (value) => {
    navigate("/history", { state: { accountNumber: value } });
  };

  useEffect(() => {
    axios
      .post(
        `${API_URL}/bank/v1/search/registedAccounts`,
        JSON.stringify({ userId: user.userId }),
        { headers: { "Content-Type": "application/json" } }
      )
      .then((res) => {
        console.log(res.data);
        setAccountList(res.data);
      })
      .catch((err) => console.log(err));
  }, []);

  return (
    <div className="w-full h-full bg-white py-16 px-5">
      {openProfileEdit ? (
        <ProfileEditCard
          setOpenProfileEdit={setOpenProfileEdit}
          user={props.user}
        />
      ) : openAddAccount ? (
        <AccountListCard
          accountList={accountList}
          setOpenAddAccount={setOpenAddAccount}
          setAccountList={setAccountList}
        />
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
              <div className="relative w-5 h-5 rounded-full bg-outline">
              <svg
                xmlns="http://www.w3.org/2000/svg"
                height="0.7em"
                viewBox="0 0 512 512"
                className="fill-white absolute left-[4.5px] top-[4.5px]"
              >
                <path d="M362.7 19.3L314.3 67.7 444.3 197.7l48.4-48.4c25-25 25-65.5 0-90.5L453.3 19.3c-25-25-65.5-25-90.5 0zm-71 71L58.6 323.5c-10.4 10.4-18 23.3-22.2 37.4L1 481.2C-1.5 489.7 .8 498.8 7 505s15.3 8.5 23.7 6.1l120.3-35.4c14.1-4.2 27-11.8 37.4-22.2L421.7 220.3 291.7 90.3z" />
              </svg>
              </div>
              계좌 목록 관리
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
