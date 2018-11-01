package com.dataart.maltahackaton.blockchain;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.EventValues;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Int256;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import rx.Observable;
import rx.functions.Func1;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.2.0.
 */
public class BlockchainCharity extends Contract {
    private static final String BINARY = "60806040526002805460a060020a60ff021916905560001960078190556000600855600c5534801561003057600080fd5b5060405160c08061088583398101604090815281516020830151918301516060840151608085015160a0909501516000805433600160a060020a031991821681178355600180548316909117905560028054909116600160a060020a0390961695909517909455603c90940242016003556004919091556005556006929092556009556107c29081906100c390396000f3006080604052600436106100cf5763ffffffff7c01000000000000000000000000000000000000000000000000000000006000350416630d3b1506811461028b5780631209b1f6146102b257806312ae74c6146102c75780632433b2a1146102dc57806329dcb0cf146102f35780632c906ba21461030857806377e1a9fc1461031d5780637b3e5e7b1461034e5780638da5cb5b146103635780639cdc9c0f14610378578063a631c3601461038d578063a9034ff9146103a2578063d0a753af146103b7578063f1f2c319146103f0575b6000806000806100dd61077f565b60025460a060020a900460ff16156100f457600080fd5b60035442111561010357600080fd5b3494503393506006548581151561011657fe5b061561012157600080fd5b6006548581151561012e57fe5b049250600091505b828210156102845750600780546001908101808355604080518082018252918252600160a060020a03808816602080850182815260068054600880549091019055600b8054808a0182556000918252885160029182027f0175b7a638427703f0dbe7bb9bbf987a2551717b34e79f33b5b1008d1fa01db981019190915584517f0175b7a638427703f0dbe7bb9bbf987a2551717b34e79f33b5b1008d1fa01dba909101805491891673ffffffffffffffffffffffffffffffffffffffff19928316179055868352600a86528883208054808d018255908452928690208a51939092029091019182559251980180549890951697909116969096179092559454935482519586529085019390935283810192909252905190917f3808dcec579c2bc05ae34288071d575efca12f2d30294c875b58c0e770802c59919081900360600190a1600190910190610136565b5050505050005b34801561029757600080fd5b506102a0610414565b60408051918252519081900360200190f35b3480156102be57600080fd5b506102a061041a565b3480156102d357600080fd5b506102a0610420565b3480156102e857600080fd5b506102f1610426565b005b3480156102ff57600080fd5b506102a0610481565b34801561031457600080fd5b506102f1610487565b34801561032957600080fd5b5061033261051f565b60408051600160a060020a039092168252519081900360200190f35b34801561035a57600080fd5b506102a061052e565b34801561036f57600080fd5b50610332610534565b34801561038457600080fd5b506102a0610543565b34801561039957600080fd5b506102a0610549565b3480156103ae57600080fd5b506102a061054f565b3480156103c357600080fd5b506103cf600435610555565b60408051928352600160a060020a0390911660208301528051918290030190f35b3480156103fc57600080fd5b506103cf600160a060020a036004351660243561058a565b60095481565b60065481565b60045481565b600054600160a060020a0316331461043d57600080fd5b60025460a060020a900460ff16151560011461045857600080fd5b6040513390303180156108fc02916000818181858888f19350505050151561047f57600080fd5b565b60035481565b600080600080600354421015151561049e57600080fd5b60025460a060020a900460ff16156104b557600080fd5b6104bd6105ce565b6004543031945060649085020492506005548311156104dc5760055492505b82840391506104ea82610608565b90506104f5816106df565b50506002805474ff0000000000000000000000000000000000000000191660a060020a1790555050565b600254600160a060020a031681565b60085481565b600154600160a060020a031681565b60055481565b60075481565b600c5481565b600b80548290811061056357fe5b600091825260209091206002909102018054600190910154909150600160a060020a031682565b600a602052816000526040600020818154811015156105a557fe5b600091825260209091206002909102018054600190910154909250600160a060020a0316905082565b600c54600019146105de57600080fd5b60075460408051448152426020820152815190819003909101902081151561060257fe5b07600c55565b6002546000908190819060a060020a900460ff16151560011461062a57600080fd5b6009546064908502049150600b600c5481548110151561064657fe5b60009182526020822060016002909202010154604051600160a060020a039091169250829184156108fc02918591818181858888f19350505050158015610691573d6000803e3d6000fd5b5060408051600160a060020a03831681526020810184905281517ffde2100491ce344243a183b10b9802396310b8006e3d8ddc42dcf3844bd8fe5c929181900390910190a150909103919050565b60025460a060020a900460ff1615156001146106fa57600080fd5b600254604051600160a060020a039091169082156108fc029083906000818181858888f19350505050158015610734573d6000803e3d6000fd5b5060025460408051600160a060020a0390921682526020820183905280517f7d5ad754d53ba2f294ac966572f88b0dc3e82ddf21bfa9816e42aec5ef89a7759281900390910190a150565b6040805180820190915260008082526020820152905600a165627a7a72305820cf9f96d471746d26345c37ef1e1640ae3cb01b77e50de9d9a769102957dbd2370029";

    protected BlockchainCharity(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected BlockchainCharity(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public List<BuyTicketEventResponse> getBuyTicketEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("BuyTicket", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<BuyTicketEventResponse> responses = new ArrayList<BuyTicketEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            BuyTicketEventResponse typedResponse = new BuyTicketEventResponse();
            typedResponse.holder = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.ticketNumber = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.ticketPrice = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<BuyTicketEventResponse> buyTicketEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("BuyTicket", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, BuyTicketEventResponse>() {
            @Override
            public BuyTicketEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                BuyTicketEventResponse typedResponse = new BuyTicketEventResponse();
                typedResponse.holder = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.ticketNumber = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.ticketPrice = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public List<WinnerTransferEventResponse> getWinnerTransferEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("WinnerTransfer", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<WinnerTransferEventResponse> responses = new ArrayList<WinnerTransferEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            WinnerTransferEventResponse typedResponse = new WinnerTransferEventResponse();
            typedResponse.winner = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<WinnerTransferEventResponse> winnerTransferEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("WinnerTransfer", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, WinnerTransferEventResponse>() {
            @Override
            public WinnerTransferEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                WinnerTransferEventResponse typedResponse = new WinnerTransferEventResponse();
                typedResponse.winner = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public List<CharityTransferEventResponse> getCharityTransferEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("CharityTransfer", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<CharityTransferEventResponse> responses = new ArrayList<CharityTransferEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            CharityTransferEventResponse typedResponse = new CharityTransferEventResponse();
            typedResponse.beneficiary = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<CharityTransferEventResponse> charityTransferEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("CharityTransfer", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, CharityTransferEventResponse>() {
            @Override
            public CharityTransferEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                CharityTransferEventResponse typedResponse = new CharityTransferEventResponse();
                typedResponse.beneficiary = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public RemoteCall<BigInteger> winnerRate() {
        Function function = new Function("winnerRate", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> ticketPrice() {
        Function function = new Function("ticketPrice", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> maintenanceFeeRate() {
        Function function = new Function("maintenanceFeeRate", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> withdrawOwnersAmount() {
        Function function = new Function(
                "withdrawOwnersAmount", 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> deadline() {
        Function function = new Function("deadline", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> finishLottery() {
        Function function = new Function(
                "finishLottery", 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> charityFund() {
        Function function = new Function("charityFund", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<BigInteger> amountRaised() {
        Function function = new Function("amountRaised", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<String> owner() {
        Function function = new Function("owner", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<BigInteger> maxMaintenanceFee() {
        Function function = new Function("maxMaintenanceFee", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> currentTicketNumber() {
        Function function = new Function("currentTicketNumber", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> winnerTicketNumber() {
        Function function = new Function("winnerTicketNumber", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<Tuple2<BigInteger, String>> allTickets(BigInteger param0) {
        final Function function = new Function("allTickets", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Address>() {}));
        return new RemoteCall<Tuple2<BigInteger, String>>(
                new Callable<Tuple2<BigInteger, String>>() {
                    @Override
                    public Tuple2<BigInteger, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);;
                        return new Tuple2<BigInteger, String>(
                                (BigInteger) results.get(0).getValue(), 
                                (String) results.get(1).getValue());
                    }
                });
    }

    public RemoteCall<Tuple2<BigInteger, String>> holderTickets(String param0, BigInteger param1) {
        final Function function = new Function("holderTickets", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(param0), 
                new org.web3j.abi.datatypes.generated.Uint256(param1)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Address>() {}));
        return new RemoteCall<Tuple2<BigInteger, String>>(
                new Callable<Tuple2<BigInteger, String>>() {
                    @Override
                    public Tuple2<BigInteger, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);;
                        return new Tuple2<BigInteger, String>(
                                (BigInteger) results.get(0).getValue(), 
                                (String) results.get(1).getValue());
                    }
                });
    }

    public static RemoteCall<BlockchainCharity> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String charityAddress, BigInteger durationInMinutes, BigInteger feePercent, BigInteger maxFee, BigInteger priceForTheTicket, BigInteger winnerPercent) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(charityAddress), 
                new org.web3j.abi.datatypes.generated.Uint256(durationInMinutes), 
                new org.web3j.abi.datatypes.generated.Uint256(feePercent), 
                new org.web3j.abi.datatypes.generated.Uint256(maxFee), 
                new org.web3j.abi.datatypes.generated.Uint256(priceForTheTicket), 
                new org.web3j.abi.datatypes.generated.Uint256(winnerPercent)));
        return deployRemoteCall(BlockchainCharity.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static RemoteCall<BlockchainCharity> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String charityAddress, BigInteger durationInMinutes, BigInteger feePercent, BigInteger maxFee, BigInteger priceForTheTicket, BigInteger winnerPercent) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(charityAddress), 
                new org.web3j.abi.datatypes.generated.Uint256(durationInMinutes), 
                new org.web3j.abi.datatypes.generated.Uint256(feePercent), 
                new org.web3j.abi.datatypes.generated.Uint256(maxFee), 
                new org.web3j.abi.datatypes.generated.Uint256(priceForTheTicket), 
                new org.web3j.abi.datatypes.generated.Uint256(winnerPercent)));
        return deployRemoteCall(BlockchainCharity.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static BlockchainCharity load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new BlockchainCharity(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static BlockchainCharity load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new BlockchainCharity(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static class BuyTicketEventResponse {
        public String holder;

        public BigInteger ticketNumber;

        public BigInteger ticketPrice;
    }

    public static class WinnerTransferEventResponse {
        public String winner;

        public BigInteger amount;
    }

    public static class CharityTransferEventResponse {
        public String beneficiary;

        public BigInteger amount;
    }
}
