import axios from "axios";
import { API_URL } from "../../../lib/constants";
import { useState } from "react";

const AccountCard = (props) => {
  const user = JSON.parse(window.localStorage.getItem("User"));
  const [account, setAccount] = useState(props.account.accountList[0]);
  const onRegistButtonClick = () => {
    console.log(user.userId);
    console.log(account.accountNumber);
    axios
      .post(
        `${API_URL}/bank/v1/auth/saveaccount`,
        JSON.stringify({
          accountNumber: account.accountNumber,
          userId: user.userId,
        }),
        { headers: { "Content-Type": "application/json" } }
      )
      .then((res) => {
        console.log(res);
        props.setAccountList((prev) => [
          ...prev,
          {
            accountList: [
              {
                accountName: account.accountName,
                accountNumber: account.accountNumber,
                accountBalance: account.accountBalance,
                registed: true,
                userId: user.userNickname,
              },
            ],
          },
        ]);
        let tmpAccount = {...account}
        tmpAccount.registed = true;
        setAccount(tmpAccount)
      })
      .catch((err) => console.log(err));
  };

  const onDeleteButtonClick = () => {
    axios
      .post(
        `${API_URL}/bank/v1/auth/deleteAccount`,
        JSON.stringify({
          accountNumber: account.accountNumber,
        }),
        { headers: { "Content-Type": "application/json" } }
      )
      .then(() => {
        let tmpAccount = {...account}
        tmpAccount.registed = false;
        setAccount(tmpAccount)
        
      })
      .catch((err) => console.log(err));
  }

  return (
    <div
      className="card shadow-none bg-background-fill flex-row p-5 w-full gap-3 rounded-lg"
      onClick={
        props.type === "history"
          ? () => props.onClickEvent(account.accountNumber)
          : props.type === "pay"
          ? () => props.setShowAccount(false)
          : null
      }
    >
      <figure>
        <img
          src="./shc_symbol_ci.png"
          alt="Shoes"
          className="rounded-xl w-10 h-10"
        />
      </figure>
      <div className="card-body flex-row items-center text-center p-0 justify-between">
        <div className="flex flex-col items-start">
          <div className="text-base font-bold">{account.accountName}</div>
          <div className="text-sm text-outline">{account.accountNumber}</div>
        </div>
        <div className="card-actions items-center gap-2">
          <div className="text-lg font-bold">
            {Number(account.accountBalance).toLocaleString()}원
          </div>
          {props.type === "addAccount" ? (
            account.registed ? (
              <button onClick={onDeleteButtonClick}>
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  height="1.5em"
                  viewBox="0 0 448 512"
                  className="fill-myprimary"
                >
                  <path d="M432 256c0 17.7-14.3 32-32 32L48 288c-17.7 0-32-14.3-32-32s14.3-32 32-32l352 0c17.7 0 32 14.3 32 32z" />
                </svg>
              </button>
            ) : (
              <button onClick={onRegistButtonClick}>
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  height="1.5em"
                  viewBox="0 0 448 512"
                  className="fill-myprimary"
                >
                  <path d="M256 80c0-17.7-14.3-32-32-32s-32 14.3-32 32V224H48c-17.7 0-32 14.3-32 32s14.3 32 32 32H192V432c0 17.7 14.3 32 32 32s32-14.3 32-32V288H400c17.7 0 32-14.3 32-32s-14.3-32-32-32H256V80z" />
                </svg>
              </button>
            )
          ) : null}
        </div>
      </div>
    </div>
  );
};

export default AccountCard;
