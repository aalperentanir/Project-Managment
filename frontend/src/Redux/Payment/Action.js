import api from "../../Config/api";

export const createPayment = async({subType,jwt}) => {

    try {
        const {data} = await api.post(`/api/payment/${subType}`)
        if(data.payment_url){
            window.location.href=data.payment_url;
        }
    } catch (error) {
        console.log("error",error)
    }
}