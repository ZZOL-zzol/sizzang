import AccountCard from "./AccountCard";
import SearchBar from "../common/SearchBar";

const accountList = [
  {
    accountCode: 1,
    accountHolder: "차차아버님",
    accountNumber: "123-456-789",
    accountName: "차차야그만방해해계좌",
    accountBalance: 200000,
  },
  {
    accountCode: 2,
    accountHolder: "차차아버님",
    accountNumber: "123-456-789",
    accountName: "차차야그만방해해계좌",
    accountBalance: 200000,
  },
  {
    accountCode: 3,
    accountHolder: "차차아버님",
    accountNumber: "123-456-789",
    accountName: "차차야그만방해해계좌",
    accountBalance: 200000,
  },
];

const AccountListCard = (props) => {
  return (
    <div className="flex flex-col gap-3">
      <div className="flex w-full">
        <button
          className="btn btn-ghost normal-case text-xl font-environmentR"
          onClick={() => props.setOpenAddAccount(false)}
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            height="1em"
            viewBox="0 0 384 512"
          >
            <path d="M342.6 150.6c12.5-12.5 12.5-32.8 0-45.3s-32.8-12.5-45.3 0L192 210.7 86.6 105.4c-12.5-12.5-32.8-12.5-45.3 0s-12.5 32.8 0 45.3L146.7 256 41.4 361.4c-12.5 12.5-12.5 32.8 0 45.3s32.8 12.5 45.3 0L192 301.3 297.4 406.6c12.5 12.5 32.8 12.5 45.3 0s12.5-32.8 0-45.3L237.3 256 342.6 150.6z" />
          </svg>
        </button>

        <SearchBar placeholder="계좌명을 검색하세요"></SearchBar>
      </div>

      <div className="font-bold self-start">내 계좌</div>
      {accountList.map((account) => (
        <AccountCard
          account={account}
          type="addAccount"
          key={account.accountCode}
        />
      ))}
    </div>
  );
};

export default AccountListCard;
