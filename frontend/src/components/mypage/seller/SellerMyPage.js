import React, { useEffect, useState } from "react";
import PageTitle from "../../../components/common/PageTitle";
import ProfileSection from "../../../components/mypage/ProfileSection";
import AccountSection from "../../../components/mypage/account/AccountSection";
import MenuSection from "../../../components/mypage/MenuSection";
import ProfileEditCard from "../ProfileEditCard";
import AccountListCard from "../account/AccountListCard";
import ProductEditCard from "./ProductEditCard";
import axios from "axios";
import { API_URL } from "../../../lib/constants";
import HistoryEditCard from "./HistoryEditCard";

const SellerMyPage = (props) => {
  const user = props.user;
  const [store, setStore] = useState({});
  const [accountList, setAccountList] = useState([]);
  const [openProfileEdit, setOpenProfileEdit] = useState(false);
  const [openAddAccount, setOpenAddAccount] = useState(false);
  const [openProductEdit, setOpenProductEdit] = useState(false);
  const [openHistoryEdit, setOpenHistoryEdit] = useState(false);
  const [openReviewEdit, setOpenReviewEdit] = useState(false);

  useEffect(() => {
    axios
      .get(`${API_URL}/store/user/${user.userCode}`)
      .then((res) => {
        setStore(res.data.data[0]);
      })
      .catch((err) => console.log(err));

    axios
      .post(
        `${API_URL}/bank/v1/search/registedAccounts`,
        JSON.stringify({ userId: user.userId }),
        { headers: { "Content-Type": "application/json" } }
      )
      .then((res) => {
        console.log(res.data);
        setAccountList(res.data[0].accountList);
      })
      .catch((err) => console.log(err));
  }, []);

  return (
    <div className="SellerMyPage w-full h-full bg-white px-5 py-16">
      {openProfileEdit ? (
        <ProfileEditCard
          setOpenProfileEdit={setOpenProfileEdit}
          user={props.user}
          store={store}
        />
      ) : openAddAccount ? (
        <AccountListCard setOpenAddAccount={setOpenAddAccount} />
      ) : openProductEdit ? (
        <ProductEditCard
          setOpenProductEdit={setOpenProductEdit}
          store={store}
          stCode={14}
        />
      ) : openHistoryEdit ? (
        <HistoryEditCard
          setOpenHistoryEdit={setOpenHistoryEdit}
          account={accountList[0]}
        />
      ) : openReviewEdit ? (
        <div></div>
      ) : (
        <div className="flex flex-col gap-5">
          <ProfileSection
            user={props.user}
            store={store}
            setOpenProfileEdit={setOpenProfileEdit}
          />
          <AccountSection
            account={accountList[0]}
            setOpenAddAccount={setOpenAddAccount}
          />
          <div className="flex flex-col w-full gap-5">
            <MenuSection
              imageUrl="../product.svg"
              menuName="상품관리"
              bgColor="bg-secondary-container"
              onClickEvent={() => setOpenProductEdit(true)}
            />
            <MenuSection
              imageUrl="../mypage.svg"
              menuName="매출관리"
              bgColor="bg-error-container"
              onClickEvent={() => setOpenHistoryEdit(true)}
            />
          </div>
        </div>
      )}
    </div>
  );
};

export default SellerMyPage;
