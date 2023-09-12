
const TextInput = (props) => {
    return(
        <input type="text" placeholder={props.placeholder} value={props.value} onChange={props.onChangeEvent} className="input w-full border-2 border-blue-100 focus:outline-blue-500" />
    )
};

export default TextInput;