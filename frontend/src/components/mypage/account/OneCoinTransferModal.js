import TextInput from "../../common/TextInput";

const OneCoinTransferModal = (props) => {
  return (
    <div>
      <div
        className="flex justify-center items-center gap-2 text-outline"
        onClick={() => document.getElementById("my_modal_1").showModal()}
      >
        <svg
          className="fill-outline"
          xmlns="http://www.w3.org/2000/svg"
          height="1em"
          viewBox="0 0 512 512"
        >
          <path d="M256 512A256 256 0 1 0 256 0a256 256 0 1 0 0 512zM232 344V280H168c-13.3 0-24-10.7-24-24s10.7-24 24-24h64V168c0-13.3 10.7-24 24-24s24 10.7 24 24v64h64c13.3 0 24 10.7 24 24s-10.7 24-24 24H280v64c0 13.3-10.7 24-24 24s-24-10.7-24-24z" />
        </svg>
        새 계좌 등록
      </div>
      <dialog id="my_modal_1" className="modal">
        <div className="modal-box relative flex flex-col justify-between">
          <div className="modal-action">
            <form method="dialog">
              <button className="absolute btn bg-transparent flex top-5 right-5 border-none">
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  height="1.5em"
                  viewBox="0 0 384 512"
                >
                  <path d="M342.6 150.6c12.5-12.5 12.5-32.8 0-45.3s-32.8-12.5-45.3 0L192 210.7 86.6 105.4c-12.5-12.5-32.8-12.5-45.3 0s-12.5 32.8 0 45.3L146.7 256 41.4 361.4c-12.5 12.5-12.5 32.8 0 45.3s32.8 12.5 45.3 0L192 301.3 297.4 406.6c12.5 12.5 32.8 12.5 45.3 0s12.5-32.8 0-45.3L237.3 256 342.6 150.6z" />
                </svg>
              </button>
            </form>
          </div>

          <h3 className="font-bold text-lg">새 계좌 등록</h3>
          <div className="flex flex-col gap-2">
            {props.oneCoinTransfer ? (
              <div className="flex gap-2 items-center text-start my-3">
                신한은행 입금내역을 확인하고 입금명 'ZZOL' 앞 네자리 숫자를
                확인해주세요!
              </div>
            ) : (
              <div className="flex gap-2 items-center">
                <img
                  src="./shc_symbol_ci.png"
                  alt="shinhan_logo"
                  className="w-8"
                />
                신한은행
              </div>
            )}

            <TextInput
              placeholder="계좌이름"
              onChangeEvent={props.onAccountNameChange}
              value={props.newAccountName}
            />
            <TextInput
              placeholder="계좌번호"
              onChangeEvent={props.onAccountNumberChange}
              value={props.newAccountNumber}
            />
            {props.oneCoinTransfer ? (
              <TextInput
                placeholder="네자리 숫자"
                onChangeEvent={props.onOneCoinTextChange}
                value={props.oneCoinText}
              />
            ) : null}
          </div>
          {props.oneCoinTransfer ? (
            <div
              className="w-full rounded-lg h-[40px] bg-myprimary mt-5 flex items-center justify-center text-white text-lg font-medium"
              onClick={() => props.onAuthenticateButtonClick()}
            >
              인증하기
            </div>
          ) : (
            <div
              className="w-full rounded-lg h-[40px] bg-myprimary mt-5 flex items-center justify-center text-white text-lg font-medium"
              onClick={props.onOneCoinButtonClick}
            >
              1원 보내기
            </div>
          )}
        </div>
      </dialog>
    </div>
  );
};

export default OneCoinTransferModal;
