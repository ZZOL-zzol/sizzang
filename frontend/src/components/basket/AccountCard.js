// const account = {
//   accountCode: 1,
//   accountHolder: "차차아버님",
//   accountNumber: "123-456-789",
//   accountName: "차차야그만방해해계좌",
//   accountBalance: 200000,
// };

const AccountCard = (props) => {
  return (
    <div className="card flex-row bg-white shadow-xl p-5 w-full gap-3" onClick={()=>props.setShowAccount(false)}>
      <figure>
        <img src="./chacha2.jpg" alt="Shoes" className="rounded-xl w-10 h-10" />
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
