import axios from "axios";
import { API_URL } from "../../../lib/constants";

const AccountCard = (props) => {
  const user = JSON.parse(window.localStorage.getItem("User"));
  // console.log(props.account.accountNumber)

  const onRegistButtonClick = () => {
    axios.post(
      `${API_URL}/bank/v1/auth/saveaccount`,
      JSON.stringify({
        accountNumber: props.account.accountNumber,
        userId: user.userId,
      }), {headers:{"Content-Type": "application/json"}})
      .then(res => console.log(res))
      .catch(err => console.log(err))
  };

  return (
    <div
      className="card shadow-none bg-background-fill flex-row p-5 w-full gap-3"
      onClick={
        props.type === "history"
          ? ()=>props.onClickEvent(props.account.accountNumber)
          : props.type === "pay"
          ? () => props.setShowAccount(false)
          : null
      }
    >
      <figure>
        <img src="./chacha2.jpg" alt="Shoes" className="rounded-xl w-10 h-10" />
      </figure>
      <div className="card-body flex-row items-center text-center p-0 justify-between">
        <div className="flex flex-col items-start">
          <div className="text-base font-bold">{props.account.accountName}</div>
          <div className="text-sm text-outline">
            {props.account.accountNumber}
          </div>
        </div>
        <div className="card-actions items-center gap-2">
          <div className="text-lg font-bold">
            {props.account.accountBalance.toLocaleString()}원
          </div>
          {props.type === "addAccount" ? (
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
          ) : null}
        </div>
      </div>
    </div>
  );
};

export default AccountCard;
