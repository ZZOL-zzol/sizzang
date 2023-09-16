import { useState } from "react";

const AccountCard = (props) => {
  return (
    <div
      className={
        props.selectedAccountNumber === props.account.accountNumber
          ? "card flex-row bg-background-fill p-5 w-full gap-3 border-myprimary border-2"
          : "card flex-row bg-background-fill p-5 w-full gap-3"
      }
      onClick={props.onClickEvent}
    >
      <figure>
        <img src="./shc_symbol_ci.png" alt="Shoes" className="rounded-xl w-10 h-10" />
      </figure>
      <div className="card-body flex-row items-center text-center p-0 justify-between">
        <div className="flex flex-col items-start">
          <div className="text-lg font-bold">{props.account.accountName}</div>
          <div>{props.account.accountNumber}</div>
        </div>
        <div className="card-actions">
          <div>{props.account.accountBalance}</div>
        </div>
      </div>
    </div>
  );
};

export default AccountCard;
