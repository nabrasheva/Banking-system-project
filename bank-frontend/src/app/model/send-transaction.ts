export interface SendTransaction{
  sentAmount:number,
  receiverIban:string,
  reason:string,
  creditPayment:boolean
}
