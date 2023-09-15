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

const SellerMyPage = (props) => {
  const user = JSON.parse(window.localStorage.getItem("User"));
  const [store, setStore] = useState({});
  const [openProfileEdit, setOpenProfileEdit] = useState(false);
  const [openAddAccount, setOpenAddAccount] = useState(false);
  const [openProductEdit, setOpenProductEdit] = useState(false);
  const [openHistoryEdit, setOpenHistoryEdit] = useState(false);
  const [openReviewEdit, setOpenReviewEdit] = useState(false);

  useEffect(() => {
    axios
      .get(`${API_URL}/store/user/${user.userCode}`)
      .then((res) => setStore(res.data.data[0]))
      .catch((err) => console.log(err));
  }, []);

  return (
    <div className="SellerMyPage w-full h-full bg-white">
      {openProfileEdit ? (
        <ProfileEditCard
          setOpenProfileEdit={setOpenProfileEdit}
          user={props.user}
          store={store}
        />
      ) : openAddAccount ? (
        <AccountListCard setOpenAddAccount={setOpenAddAccount} />
      ) : openProductEdit ? (
        <ProductEditCard setOpenProductEdit={setOpenProductEdit} />
      ) : openHistoryEdit ? (
        <div></div>
      ) : openReviewEdit ? (
        <div></div>
      ) : (
        <div className="flex flex-col p-5 gap-5">
          <ProfileSection
            user={props.user}
            store={store}
            setOpenProfileEdit={setOpenProfileEdit}
          />
          <AccountSection
            imageUrl="../chacha2.jpg"
            accountName="차곡차곡계좌"
            accountNumber="121254-4564"
            balance="420원"
            setOpenAddAccount={setOpenAddAccount}
          />
          <div className="flex w-full justify-between">
            <MenuSection
              imageUrl="../chacha2.jpg"
              menuName="상품관리"
              bgColor="bg-secondary-container"
              onClickEvent={() => setOpenProductEdit(true)}
            />
            <MenuSection
              imageUrl="../chacha2.jpg"
              menuName="매출관리"
              bgColor="bg-error-container"
              onClickEvent={() => setOpenHistoryEdit(true)}
            />
            <MenuSection
              imageUrl="../chacha2.jpg"
              menuName="리뷰관리"
              bgColor="bg-primary-container"
              onClickEvent={() => setOpenReviewEdit(true)}
            />
          </div>
        </div>
      )}
    </div>
  );
};

export default SellerMyPage;
